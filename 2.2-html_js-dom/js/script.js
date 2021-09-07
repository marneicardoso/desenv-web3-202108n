
// alert("Hello World");

// Comentários em Linha

/* Comentários
de Bloco
*/

// Padrão de Nomenclatura de Functions (Métodos)
// Deve iniciar com letra minúscula
// Deve seguir o Padrão CamelCase
// Ex: calcularNotas()
// Recomendado que utilize um verbo no INFINITIVO + Substantivo ou Adjetivo
// O nome DEVE ser o mais descritivo possível
// int a = 5; <== RUIM
// int nota1 = 5; <== BOM

// O que é um Verbo no Infinitivo?
// R: Um verbo NÃO CONJUGADO

// Exemplo 1: Mudar valores com .value
function mudarTexto() {
    document.getElementById('texto').value = "Novo texto";
}

// Exemplo 2: Mudar valores com .innerHTML
function mudarTexto2() {
    // document.getElementById('texto2').innerHTML = "Novo texto";

    // Diferença entre innerText e innerHTML
    //document.getElementById('texto2').innerText = "Novo<br> texto";
}

// Exemplo 3: Mudar a imagem
function mudarImagem() {
    document.querySelector('#img01').src = "img/farol.jpg";
    //document.getElementById('img01').src = "img/farol.jpg";
}

// Exemplo 4: Mudar o CSS (estilo)
function mudarCSS() {
    // Variáveis
    var texto = document.querySelector('#texto3');

    texto.style.color = "red";
    // texto.style.fontSize = "24px";
    texto.style.fontWeight = "bold";
    texto.style.backgroundColor = "#e9b200";

    // console.log("Valor:" + texto.innerText);
    // alert("Valor: " + texto);
}

// Exemplo 5: Mudar o texto (com uso de parâmetro)
function mudarTexto3(id) {
    
    console.log(id.innerText);

    id.innerHTML = "Texto alterado por JavaScript";
}

// Exemplo 6: Botão com Data e Hora
function mostrarDataHora() {
    // document.querySelector('#dataHora').innerHTML = Date();
    document.getElementById('dataHora').innerHTML = Date();
}

// Exemplo 7: Alterar texto ao tirar foco
function alterarTexto() {
    const textoInformado = document.querySelector('#nome');
    textoInformado.value = textoInformado.value.toUpperCase();
    // textoInformado.style.border = "solid red 2px";

    // const textoInformado = document.querySelector('#nome');
    // textoInformado.value = textoInformado.value.toLowerCase();
}

// Exemplo 8: Passar o mouse sobre
function passarMouse(objeto) {
    // alert(objeto);
    objeto.innerHTML = "Obrigado!";
    objeto.style.backgroundColor = "#59a6d3";
    objeto.style.transition = "0.5s";
}

function tirarMouse(objeto) {
    objeto.innerHTML = "Passe o mouse";
    objeto.style.backgroundColor = "#f9f9f9";
    objeto.style.transition = "1.5s";
}

// Exemplo 9: Clicar, segurar e soltar
function clicar(elemento) {
    elemento.innerHTML = "Solte!";
    elemento.style.backgroundColor = "#1ec5e5";
}

function soltar(elemento) {
    elemento.innerHTML = "Clicar!";
    elemento.style.backgroundColor = "#d94a38";
}

// #Desafio 1:
function capturar() {
    var capturando = document.getElementById('nome').value;
    document.getElementById('valorDigitado').innerHTML = capturando;
}


// Extra: Contador de cliques

// Atributo (variável) Global
let contador = 0;

function contadorClique() {
    // Incrementa o contador
    contador++;     // Conta de 1 em 1
    // contador+=1;    // Conta de N em N (onde N é o valor)
    // contador = contador + 1;

    // Atualiza o Display
    atualizarDisplay();
}

function zerarContador() {
    // Zera o contador (a variável)
    contador = 0;

    // Atualiza o Display
    atualizarDisplay();
}

function atualizarDisplay() {
    document.getElementById('display').value = contador;
    // document.getElementById('display').focus();
}