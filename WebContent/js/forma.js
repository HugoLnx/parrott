(function(){
  var divCartao = document.getElementById("divCartao");
  var divDebito = document.getElementById("divDebito");
  var divInfoCartao = document.getElementById("infoCartao");
  var inputCartao = document.getElementById("cartao");
  var inputDebito = document.getElementById("debito");
  var inputBoleto = document.getElementById("boleto");

  inputCartao.addEventListener("change", function() {
    show(divCartao);
    hide(divDebito);
    show(divInfoCartao);
  }, false);

  inputDebito.addEventListener("change", function() {
    show(divDebito);
    hide(divCartao);
    hide(divInfoCartao);
  }, false);

  inputBoleto.addEventListener("change", function() {
    hide(divDebito);
    hide(divCartao);
    hide(divInfoCartao);
  }, false);

  function hide(el) {
    el.style.display="none";
  }

  function show(el) {
    el.style.display="block";
  }
}());
