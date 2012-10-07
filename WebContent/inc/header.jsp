<div class="navbar navbar-fixed-top">
	<div class="navbar-inner">
		<a class="brand" href="/">
			<img alt="parrot-logo" src="<c:url value="/img/parrot16.png" />" class="logoImg"/>
			<span class="logo">Parrott</span>
		</a>
		<ul class="nav">
			<li><a href="<c:url value="/"/>">In�cio</a></li>
			<li class="dropdown">
				<a href="#" class="dropdown-toggle" data-toggle="dropdown">Mais Buscados<b class="caret"></b></a>
				<ul class="dropdown-menu">
					<li><a href="<c:url value="/timeline/tenderlove"/>">Tender Love</a></li>
					<li><a href="<c:url value="/timeline/unclebob"/>">Uncle	Bob</a></li>
					<li><a href="<c:url value="/timeline/akitaonrails"/>">F�bio	Akita</a></li>
					<li><a href="<c:url value="/timeline/guilhermesilveira"/>">Guilherme Silveira</a></li>
				</ul>
			</li>
			<li><a href="<c:url value="/sobre.jsp"/>">Sobre</a></li>
		</ul>




		<ul id="nav-forms" class="nav pull-right">
			<li>
				<form class="navbar-form form-search" action="<c:url value="/timeline/" />">
					<div class="input-append">
						<input name="username" type="text" placeholder="usu�rio do github..." class="span2 search-query">
						<input type="submit" class="btn" value="Commits">
	
					</div>
					<a href="#" class="bspopover" rel="popover" data-trigger="hover"
							data-placement="bottom"
							data-original-title="Busca por usu�rio"
							data-content="Digite o usu�rio do GitHub que voc� quer ver os commits">
						<i class="icon-question-sign"></i>
					</a>
				</form>
			</li>

			<li>
			<form class="navbar-form form-search" action="<c:url value="/following/" />">
				<div class="input-append">
					<input name="username" type="text" placeholder="usu�rio do github..." class="span2 search-query">
					<input type="submit" class="btn" value="Seguindo">
				</div>
				
				<a href="#" class="bspopover" rel="popover" data-trigger="hover"
						data-placement="bottom"
						data-original-title="Busca por followings"
						data-content="Digite seu usu�rio do GitHub e veja os commits de quem voc� est� seguindo!">
					<i class="icon-question-sign"></i>
				</a>
			</form>
			</li>
		</ul>
	</div>
</div>

<header class="hero-unit">
	<h1 class="header">
		<a href="<c:url value="/" />" title="In�cio"><img alt="parrot-logo" src="<c:url value="/img/parrot64.png" />" class="logoImg"><span class="logo">Parrott</span></a>
		<small> - Descubra, de maneira r�pida, os commits que fizeram no <a href="http://github.com">GitHub</a>!</small>
	</h1> 
</header>
