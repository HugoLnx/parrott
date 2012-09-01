if (typeof Caelum === "undefined") {
  var Caelum = {};
}

if (Caelum.PagPag === undefined) {
  Caelum.PagPag = {};
}

(function(PagPag) {
  Mascara = {};
  PagPag.Mascara = Mascara;

  Mascara.adicionar = function(nome, mascara) {
    document.getElementsByName(nome)[0].onkeydown = function(){
      aplicarMascara(this, mascara);
    };

    document.getElementsByName(nome)[0].onkeypress = function(){
      aplicarMascara(this, mascara);
    };

    document.getElementsByName(nome)[0].onkeyup = function(){
      aplicarMascara(this, mascara);
    };
  }

  /*Função que permite apenas numeros*/
  Mascara.Integer = function(v){
    return v.replace(/\D/g,"");
  }

  /*Função que padroniza telefone (11)4184-1241*/
  Mascara.Telefone = function(v){
    v=v.replace(/\D/g,"");                            
    v=v.replace(/^(\d\d)(\d)/g,"($1)$2"); 
    v=v.replace(/(\d{4})(\d)/,"$1-$2");      
    return v;
  }

  /*Função que padroniza CPF*/
  Mascara.Cpf = function(v){
    v=v.replace(/\D/g,"");                                   
    v=v.replace(/(\d{3})(\d)/,"$1.$2");         
    v=v.replace(/(\d{3})(\d)/,"$1.$2");         
                                                                                     
    v=v.replace(/(\d{3})(\d{1,2})$/,"$1-$2");

    return v;
  }
          
  /*Função que padroniza CEP*/
  Mascara.Cep = function(v){
    v=v.replace(/\D/g,"");                            
    v=v.replace(/^(\d{5})(\d)/,"$1-$2"); 
    return v;
  }

  /*Função que padroniza DATA*/
  Mascara.Data = function(v){
    v=v.replace(/\D/g,"");
    v=v.replace(/(\d{2})(\d)/,"$1/$2"); 
    v=v.replace(/(\d{2})(\d)/,"$1/$2"); 
    return v;
  }

  // Função deixa nome cartao em maiúsculo
  Mascara.NomeCartao = function(v){
    return v.toUpperCase();
  }

  function aplicarMascara(o,f){
    var v_obj=o;
    var v_fun=f;
    setTimeout(function() {
      v_obj.value=v_fun(v_obj.value);
    },1);
  }

}(Caelum.PagPag));
