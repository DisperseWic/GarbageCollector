<!DOCTYPE html>
<html>
  <head>
	<meta charset="utf-8">
	<meta name="viewport"
		  content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta name="description" content="">
	<meta name="author" content="">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
	<title>Garbage</title>
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
  <body data-logged-in-user="${username}"
        data-is-admin="${admin?c}">

  <div class="container">
    <nav class="navbar navbar-dark bg-dark">
      <a class="navbar-brand" href="/">Garbage Collector</a>
      <form class="form-inline">
        <div class="navbar-text pr-2">Вошел как ${username}</div>
        <button class="btn btn-outline-success my-2 my-sm-0" type="submit"><a href="/logout">Logout</a></button>
      </form>
    </nav>
    <br>
    <#if admin>
      <ul>
      <li><a href="/" class="link1">Главная страница</a></li>
    <li><a href="/users" class="link1">Список пользователей</a></li>
      </ul>
    </#if>
    <hr>
    <br>
    <h2>Список заявок</h2>
    <#if admin>
    <table class="table">
      <thead class="thead-dark">
      <tr>
        <th scope="col">ID</th>
        <th scope="col">Логин</th>
        <th scope="col">Заявка от</th>
        <th scope="col">Объем. м3</th>
        <th scope="col">Тип отходов</th>
        <th scope="col">Адрес</th>
        <th scope="col">Комментарий</th>
        <th scope="col">Статус заявки</th>
        <th scope="col">Удалить/Закрыть</th>
      </tr>
      </thead>
      <#list applications as app>
        <tr>
          <td>${app.id}</td>
          <td>${app.owner.username}</td>
          <td>${app.createdAt}</td>
          <td>${app.volume}</td>
          <td>${app.tip}</td>
          <td>${app.address}</td>
          <td>${app.comment}</td>
          <td>${app.status}</td>
          <td>
            <form action="/" method="POST">
              <input type="hidden" name="deleteId" value="${app.id}"/>
              <input type="hidden" name="updateId"/>
              <input type="hidden" name="address"/>
              <input type="hidden" name="volume" value="0.0"/>
              <input type="hidden" name="comment"/>
              <input type="hidden" name="tip"/>
              <button type="submit" class="btn btn-outline-danger">Удалить</button>
            </form>
            <form action="/" method="POST">
              <input type="hidden" name="updateId" value="${app.id}"/>
              <input type="hidden" name="deleteId"/>
              <input type="hidden" name="address"/>
              <input type="hidden" name="volume" value="0.0"/>
              <input type="hidden" name="comment"/>
              <input type="hidden" name="tip"/>
              <button type="submit" class="btn btn-outline-dark">Закрыть</button>
            </form>
          </td>
      </#list>
    </table>

    <#else>
    <table class="table">
      <thead class="thead-dark">
      <tr>
        <th scope="col">Заявка от</th>
        <th scope="col">Объем. м3</th>
        <th scope="col">Тип отходов</th>
        <th scope="col">Адрес</th>
        <th scope="col">Комментарий</th>
        <th scope="col">Статус заявки</th>
      </tr>
      </thead>
      <#list applications as app>
        <#if username = app.owner.username>
      <tr>
        <td>${app.createdAt}</td>
        <td>${app.volume}</td>
        <td>${app.tip}</td>
        <td>${app.address}</td>
        <td>${app.comment}</td>
        <td>${app.status}</td>
        </#if>
      </#list>
    </#if>
    </table>
    <hr>
    <h2>Подать заявку</h2>
    <br>
    <div class="container">
      <div class="row">
        <div class="col-sm">
          <form action="/" method="POST">
            <input type="hidden" name="deleteId"/>
            <input type="hidden" name="updateId"/>
            <label>Пользователь:</label>
            <input type="text" name="username" value="${username}" disabled/>
            <br>
            <label>Адрес:</label>
            <input type="text" name="address" placeholder="Россия, Пермь, ул. Революции 12" required/>
            <br>
            <label>Объем (м<sup>3</sup>):</label>
            <input type="text" name="volume" placeholder="146.6" required pattern="\d+\.?\d+"/>
            <br>
            <label>Тип отходов:</label>
            <input type="text" name="tip" placeholder="Металлолом" required/>
            <br>
            <label>Комментарий:</label>
            <input type="text" name="comment" placeholder="Ваш комментарий к заказу"/>
            <br><br>
            <div class="container" align="center">
              <button type="submit" class="btn btn-dark">Отправить заказ</button>
            </div>

          </form>
        </div>
        <div class="col-sm">
        </div>
      </div>
    </div>
<br>
    <br>


  </div>
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
  </body>
</html>
