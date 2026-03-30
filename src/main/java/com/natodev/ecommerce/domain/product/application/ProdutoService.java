package com.natodev.ecommerce.domain.product.application;

import com.natodev.ecommerce.domain.product.presentation.dto.request.ProdutoRequestDTO;
import com.natodev.ecommerce.domain.product.presentation.dto.response.ProdutoResponseDTO;
import com.natodev.ecommerce.domain.product.domain.entity.Categoria;
import com.natodev.ecommerce.domain.product.domain.entity.Produto;
import com.natodev.ecommerce.domain.product.domain.exception.CategoriaNaoExisteException;
import com.natodev.ecommerce.domain.product.domain.repository.CategoriaRepository;
import com.natodev.ecommerce.domain.product.domain.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;

    @Transactional
    public ProdutoResponseDTO salvarProduto(ProdutoRequestDTO produtoRequestDTO) {

        Produto produto = new Produto();
        produto.setNome(produtoRequestDTO.nome());
        produto.setDescricao(produtoRequestDTO.descricao());
        produto.setPreco(produtoRequestDTO.preco());
        produto.setEstoque(produtoRequestDTO.estoque());

        Categoria categoria = categoriaRepository.findById(produtoRequestDTO.categoriaProduto())
                .orElseThrow(() -> new CategoriaNaoExisteException("Categoria com id " + produtoRequestDTO.categoriaProduto() +
                        " não foi encontrado!"));

        produto.setCategoria(categoria);

        Produto produtoSalvo = produtoRepository.save(produto);

        return new ProdutoResponseDTO(produtoSalvo.getProdutoId(), produtoSalvo.getNome(), produtoSalvo.getDescricao(),
                produtoSalvo.getPreco(), produtoSalvo.getEstoque(), produtoSalvo.getCategoria().getNome());
    }

    @Transactional(readOnly = true)
    public List<ProdutoResponseDTO> listarProdutos() {
        return produtoRepository.findAll()
                .stream()
                .map(produto -> new ProdutoResponseDTO(
                        produto.getProdutoId(),
                        produto.getNome(),
                        produto.getDescricao(),
                        produto.getPreco(),
                        produto.getEstoque(),
                        produto.getCategoria().getNome()
                ))
                .collect(Collectors.toList());
    }
}