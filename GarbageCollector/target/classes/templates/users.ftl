<!DOCTYPE html>
<html>
  <head>
	<meta charset="utf-8">
	<meta name="viewport"
		  content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta name="description" content="">
	<meta name="author" content="">
      <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
	<title>Users</title>
      <style>
          label {
              width: 135px;
              display: inline-block;
              text-align: right;
          }
          input {
              width: 250px;
              display: inline-block;
          }
          form {
              display: inline;
          }
          A.link1{
              font-size: 21px;
              color: black;
          }
          A{
              fonr-size: 21px;
              color:white;
          }
          A.link1:hover {
              font-size: 21px; /* Размер шрифта */
              font-weight: bold; /* Жирное начертание */
              color: blueviolet; /* Цвет ссылки */
          }

          body{
              background-color: azure;
          }
      </style>
  </head>
  <body data-logged-in-user="${username}">
  <div class="container">
    <nav class="navbar navbar-dark bg-dark">
        <a class="navbar-brand" href="/">Garbage Collector</a>
        <form class="form-inline">
            <div class="navbar-text pr-2">Вошел как ${username}</div>
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit"><a href="/logout">Logout</a></button>
        </form>
    </nav>
    <br>
    <ul>
        <li><a href="/" class="link1">Главная страница</a></li>
        <li><a href="/users" class="link1">Список пользователей</a></li>
    </ul>
    <hr>
    <h2>Список пользователей</h2>
    <ul>
      <#list users as user>
        <li>Логин: <b>${user.username}</b>  Пароль: <b>${user.password}</b> (${user.enabled?then("<font color=\"green\">активен</font>", "<font color=\"red\">неактивен</font>")})</li>
      </#list>
    </ul>
      </div>
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
  </body>
</html>
