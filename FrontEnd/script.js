let loginData = {}; // Simula o banco de usuários
let currentUser = null;
let pedidoItens = [];
let total = 0;
let deliveryOption = '';
let address = '';

function showScreen(screenId) {
    const screens = ['login-screen', 'register-screen', 'menu-screen', 'delivery-screen', 'payment-screen'];
    screens.forEach(id => {
        document.getElementById(id).style.display = id === screenId ? 'block' : 'none';
    });
}

function register() {
    const username = document.getElementById('register-username').value;
    const password = document.getElementById('register-password').value;

    if (username && password) {
        loginData[username] = password;
        alert("Conta criada com sucesso!");
        showScreen('login-screen');
    } else {
        alert("Preencha todos os campos.");
    }
}

function login() {
    const username = document.getElementById('login-username').value;
    const password = document.getElementById('login-password').value;

    if (loginData[username] === password) {
        currentUser = username;
        showScreen('menu-screen');
    } else {
        alert("Usuário ou senha incorretos!");
    }
}

function adicionarItem(nome, preco) {
    pedidoItens.push({ nome, preco });
    total += preco;
    atualizarPedido();
}

function atualizarPedido() {
    const pedidoItensDisplay = document.getElementById('pedido-itens');
    pedidoItensDisplay.innerHTML = '';
    pedidoItens.forEach(item => {
        const li = document.createElement('li');
        li.textContent = `${item.nome} - R$${item.preco.toFixed(2)}`;
        pedidoItensDisplay.appendChild(li);
    });
    document.getElementById('total').textContent = `Total: R$${total.toFixed(2)}`;
}

function setDeliveryOption(option) {
    deliveryOption = option;
    document.getElementById('address-section').style.display = option === 'Entrega' ? 'block' : 'none';
}

function nextToDelivery() {
    showScreen('delivery-screen');
}

function nextToPayment() {
    if (deliveryOption === 'Entrega' && !document.getElementById('address').value) {
        alert("Informe o endereço.");
        return;
    }
    address = document.getElementById('address').value;
    showScreen('payment-screen');
}

function finalizarPedido() {
    const name = document.getElementById('name').value;
    const phone = document.getElementById('phone').value;
    const cpf = document.getElementById('cpf').value;

    if (!name || !phone || !cpf) {
        alert("Preencha todos os campos.");
        return;
    }

    // Salvar informações na planilha
    const data = [
        ['Usuário', currentUser],
        ['Itens', pedidoItens.map(item => item.nome).join(', ')],
        ['Total', `R$${total.toFixed(2)}`],
        ['Entrega/Balcão', deliveryOption],
        ['Endereço', address || 'N/A'],
        ['Nome', name],
        ['Telefone', phone],
        ['CPF', cpf]
    ];

    const ws = XLSX.utils.aoa_to_sheet(data);
    const wb = XLSX.utils.book_new();
    XLSX.utils.book_append_sheet(wb, ws, 'Pedido');
    XLSX.writeFile(wb, 'pedido.xlsx');

    // Limpar os dados do pedido
    pedidoItens = [];
    total = 0;
    deliveryOption = '';
    address = '';

    alert("Pedido finalizado e salvo!");
    showScreen('completion-screen');
}

function voltarAoMenu() {
    showScreen('menu-screen');
}

function encerrar() {
    alert("Obrigado por usar nosso sistema!");
    // Para simular encerramento, voltamos à tela inicial:
    showScreen('login-screen');
}