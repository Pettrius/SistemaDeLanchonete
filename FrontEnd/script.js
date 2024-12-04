document.addEventListener('DOMContentLoaded', () => {
    // Referências aos elementos do DOM
    const pedidoItens = document.getElementById('pedido-itens');
    const finalizarPedidoBtn = document.getElementById('finalizar-pedido');
    const enderecoForm = document.getElementById('address-section');
    const pedidoConfirmado = document.getElementById('pedido-confirmado');
    const pagamentoOpcoes = document.getElementById('payment-options');

    // Produtos (Exemplo)
    const produtos = [
        { nome: 'Pizza de Calabresa', preco: 40 },
        { nome: 'Refrigerante 2L', preco: 10 },
        { nome: 'Sobremesa', preco: 15 }
    ];

    // Estado global do pedido
    const pedido = {
        itens: [],
        total: 0,
        endereco: '',
        metodoPagamento: ''
    };

    // Inicializar a lista de produtos
    const carregarProdutos = () => {
        produtos.forEach((produto) => {
            const li = document.createElement('li');
            li.textContent = `${produto.nome} - R$ ${produto.preco.toFixed(2)}`;
            li.classList.add('produto-item');
            li.addEventListener('click', () => adicionarAoPedido(produto));
            pedidoItens.appendChild(li);
        });
    };

    // Adicionar item ao pedido
    const adicionarAoPedido = (produto) => {
        pedido.itens.push(produto);
        pedido.total += produto.preco;
        atualizarPedidoVisual();
    };

    // Atualizar a interface do pedido
    const atualizarPedidoVisual = () => {
        const pedidoResumo = document.getElementById('pedido-resumo');
        pedidoResumo.innerHTML = ''; // Limpar resumo anterior

        pedido.itens.forEach((item, index) => {
            const itemPedido = document.createElement('p');
            itemPedido.textContent = `${item.nome} - R$ ${item.preco.toFixed(2)}`;
            const removerBtn = document.createElement('button');
            removerBtn.textContent = 'Remover';
            removerBtn.addEventListener('click', () => removerItem(index));
            itemPedido.appendChild(removerBtn);
            pedidoResumo.appendChild(itemPedido);
        });

        const totalDiv = document.getElementById('pedido-total');
        totalDiv.textContent = `Total: R$ ${pedido.total.toFixed(2)}`;
    };

    // Remover item do pedido
    const removerItem = (index) => {
        pedido.total -= pedido.itens[index].preco;
        pedido.itens.splice(index, 1);
        atualizarPedidoVisual();
    };

    // Finalizar pedido
    finalizarPedidoBtn.addEventListener('click', () => {
        const endereco = document.getElementById('endereco').value;
        if (pedido.itens.length === 0) {
            alert('Adicione itens ao pedido antes de finalizar!');
            return;
        }

        if (!endereco) {
            alert('Por favor, preencha o endereço para entrega!');
            return;
        }

        pedido.endereco = endereco;
        enderecoForm.style.display = 'none';
        pagamentoOpcoes.style.display = 'block';
    });

    // Confirmar pagamento
    pagamentoOpcoes.addEventListener('click', (event) => {
        if (event.target.tagName === 'BUTTON') {
            pedido.metodoPagamento = event.target.dataset.pagamento;
            confirmarPedido();
        }
    });

    // Confirmar e exibir resumo do pedido
    const confirmarPedido = () => {
        pedidoConfirmado.style.display = 'block';
        pagamentoOpcoes.style.display = 'none';

        const resumoDiv = document.getElementById('resumo-pedido');
        resumoDiv.innerHTML = `
            <h3>Pedido Confirmado!</h3>
            <p>Itens:</p>
            ${pedido.itens.map(item => `<p>${item.nome} - R$ ${item.preco.toFixed(2)}</p>`).join('')}
            <p>Total: R$ ${pedido.total.toFixed(2)}</p>
            <p>Endereço: ${pedido.endereco}</p>
            <p>Pagamento: ${pedido.metodoPagamento}</p>
        `;
    };

    // Iniciar a aplicação
    carregarProdutos();
});
