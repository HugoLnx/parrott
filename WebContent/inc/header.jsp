<div class="navbar navbar-fixed-top">
	<div class="navbar-inner">
		<a class="brand" href="/">
			<img alt="parrot-logo" src="<c:url value="/img/parrot16.png" />" class="logoImg"/>
			<span class="logo">Parrott</span>
		</a>
		<ul class="nav">
			<li><a href="/">Início</a></li>
			<li class="dropdown">
				<a href="#" class="dropdown-toggle" data-toggle="dropdown">Mais Buscados <b class="caret"></b></a>
				<ul class="dropdown-menu">
					<li><a href="#">Nome 1</a></li>
					<li><a href="#">Nome 2</a></li>
					<li><a href="#">Nome 3</a></li>
				</ul>
			</li>
			<li><a href="#">Sobre</a></li>
		</ul>
		<form class="navbar-form pull-right" action="/timeline/">
			<input type="text" name="username" id="userName" placeholder="Buscar usuário..." class="input-medium search-query"/>
			<button type="submit" class="btn"><i class="icon-search" tabindex="0"></i></button>
		</form>
	</div>
</div>

<header class="hero-unit">
	<h1 class="header">
		<a href="<c:url value="/" />" title="Iní­cio"><img alt="parrot-logo" src="<c:url value="/img/parrot64.png" />" class="logoImg"><span class="logo">Parrott</span></a>
		<small> - Descubra, de maneira rápida, os commits que fizeram no <a href="http://github.com">GitHub</a>!</small>
	</h1> 
</header>