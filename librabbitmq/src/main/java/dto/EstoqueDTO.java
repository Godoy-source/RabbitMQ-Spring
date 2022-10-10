package dto;

import java.io.Serializable;

// Serializable transforma a estrutura da classe para bytes
public class EstoqueDTO implements Serializable {
    public String codigoProduto;
    public int quantidade;

}
