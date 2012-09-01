(function(Mascara) {
  Mascara.adicionar("cartao.validade", Mascara.Data);
  Mascara.adicionar("cartao.nascimentoPortador", Mascara.Data);
  Mascara.adicionar("cartao.telefonePortador", Mascara.Telefone);
  Mascara.adicionar("cartao.cpfTitular", Mascara.Cpf);
  Mascara.adicionar("cartao.nomePortador", Mascara.NomeCartao);
  Mascara.adicionar("cartao.codigo", Mascara.Integer);
  Mascara.adicionar("cartao.numero", Mascara.Integer);
}(Caelum.PagPag.Mascara));
