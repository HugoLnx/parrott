if (typeof Caelum == "undefined") {
  var Caelum = {};
}

Caelum.PagPag = {};

(function(PagPag, MoipWidget, MoipUtil) {
  function main(){
    var data = new DivPagPagData();

    PagPag.moipCallback = new MoipCallback(data);
    PagPag.moipCalcularParcelasCallback = new MoipCalcularParcelasCallback();

    var submit = document.getElementById("finalizarCompra");
    submit.addEventListener("click", function() {
      var config = new ConfiguracaoMoipWidget(data);
      MoipWidget(config);
    });

    var forma = data.get("formaPagamento");
    if(forma == "CartaoCredito") {
      setTimeout(function(){
        var config = new ConfiguracaoMoipCalculaParcela(data);
        MoipUtil.calcularParcela(config);
      }, 1000);
    }
  }

  function MoipCallback(data){
    this.funcaoSucesso = function(resposta) {
      var form = document.getElementById("confirmarPagamento");
      addHiddenInput(form, "uriRedirecionamento", resposta.url);
      form.submit();
    };

    this.funcaoFalha = function(resposta) {
      var form = document.getElementById("formErroMoip");
      var msgs = [];
      for(var i = 0; i<resposta.length; i++) {
        var falha = resposta[i];
        msgs.push(falha.Mensagem);
      }

      addHiddenInputs(form, {
        "servicoUUID": data.get("servicoUUID"),
        "cartao.parcelas": data.get("parcelas"),
        "cartao.numero": data.get("numeroCartao"),
        "cartao.validade": data.get("validadeCartao"),
        "cartao.codigo": data.get("codigoCartao"),
        "cartao.nomePortador": data.get("nomePortador"),
        "cartao.nascimentoPortador": data.get("nascimentoPortador"),
        "cartao.telefonePortador": data.get("telefonePortador"),
        "cartao.cpfTitular": data.get("cpfTitular"),
        "erros": msgs.join(";")
      });

      form.submit();
    };

    function voltarPagamento(){
      var formVoltar = document.getElementById("formVoltarPagamento");
      addHiddenInputs(formVoltar, {
        "servicoUUID": data.get("servicoUUID"),
        "cartao.parcelas": data.get("parcelas"),
        "cartao.numero": data.get("numeroCartao"),
        "cartao.validade": data.get("validadeCartao"),
        "cartao.codigo": data.get("codigoCartao"),
        "cartao.nomePortador": data.get("nomePortador"),
        "cartao.nascimentoPortador": data.get("nascimentoPortador"),
        "cartao.telefonePortador": data.get("telefonePortador"),
        "cartao.cpfTitular": data.get("cpfTitular")
      });
      
      formVoltar.submit();
    }
    
    var submitVoltar = document.getElementById("voltarCompra");
    submitVoltar.addEventListener("click", function() {
      voltarPagamento();
    });
    
    function addHiddenInputs(form, obj) {
      for(prop in obj) {
        if(obj.hasOwnProperty(prop)) {
          addHiddenInput(form, prop, obj[prop]);
        }
      }
    }

    function addHiddenInput(form, nome, valor) {
      var hidden = document.createElement("input");
      hidden.type = "hidden";
      hidden.name = nome;
      hidden.value = valor;
      form.appendChild(hidden);
    };
  };

  function ConfiguracaoMoipCalculaParcela(data) {
    this.cofre = "";
    this.instituicao = data.get("bandeira");
    this.callback = "Caelum.PagPag.moipCalcularParcelasCallback.setarValorParcelas";
  }

   function MoipCalcularParcelasCallback() {
    this.setarValorParcelas = function(data) {
      var spanParcelas = document.getElementById("valorParcelas");
      var spanTotal = document.getElementById("valorTotal");
      var qnt = parseInt(spanParcelas.textContent, 10);

      if (qnt !== 1) {
        var parcela = procurarParcela(qnt, data);
        spanParcelas.textContent = qnt + "x de R$" + parcela.valor;
        spanTotal.textContent = parcela.valor_total;
      }
    }

    function procurarParcela(qnt, data) {
      var parcelas = data.parcelas;

      for(var i = 0; i<parcelas.length; i++) {
        var parcela = parcelas[i];
        if(qnt === parseInt(parcela.quantidade, 10)) {
          return parcela;
        }
      }
      return undefined;
    }
  };

  function DivPagPagData() {
    var _div = document.getElementById("PagPagData");

    this.get = function(attr) {
      return _div.getAttribute("data-" + attr);
    };
  }

  function ConfiguracaoMoipWidget(data) {
    var forma = data.get("formaPagamento");
    if(forma == "CartaoCredito") {
      return new ConfigCredito(data);
    } else if (forma == "BoletoBancario") {
      return new ConfigBoleto(data);
    } else {
      return new ConfigDebito(data);
    }

    function ConfigCredito(data) {
      this.Forma = data.get("formaPagamento");
      this.Instituicao = data.get("bandeira");
      this.Parcelas = data.get("parcelas");
      this.Recebimento = "AVista";
      this.CartaoCredito = {
        "Numero" : data.get("numeroCartao"),
        "Expiracao" : data.get("validadeCartao"),
        "CodigoSeguranca" :  data.get("codigoCartao"),
        "Portador" : {
          "Nome" : data.get("nomePortador"),
          "DataNascimento" : data.get("nascimentoPortador"),
          "Telefone" : data.get("telefonePortador"),
          "Identidade" : data.get("cpfTitular")
        }
      };
    }

    function ConfigDebito(data) {
      this.Forma = data.get("formaPagamento");
      this.Instituicao = data.get("banco");
    }

    function ConfigBoleto(data) {
      this.Forma = data.get("formaPagamento");
    }
  }

  main();
}(Caelum.PagPag, MoipWidget, MoipUtil));
