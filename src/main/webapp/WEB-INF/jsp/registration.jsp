<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>

    <link rel='stylesheet prefetch' href='http://netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap.min.css'>

    <%--<link rel="stylesheet" href="/static/css/style.css">--%>

</head>
<body>
<div class="wrapper">

    <form class="form-signin" action="/registration2" method="post"  enctype="multipart/form-data">
        <div class="container-fluid">
            <div class=row>
                <div class="col-md-4"></div>
                <div class="col-md-4">
                    <div class="form-group">
                        <h4><c:out value="${requestScope.error}"/></h4>
                    </div>
                </div>
                <div class="col-md-4"></div>
            </div>
            <div class="row" style="height: 100px">
            </div>
            <div class="row">
                <div class="col-md-4"></div>
                <div class="col-md-4">
                    <div class="form-group">
                        <input type="text" name="name" value="${requestScope.name}" class="form-control" placeholder="Имя">
                    </div>
                </div>
                <div class="col-md-4"></div>
            </div>
            <div class="row">
                <div class="col-md-4"></div>
                <div class="col-md-4">
                    <div class="form-group">
                        <input type="text" name="surname" value="${requestScope.surname}" class="form-control" placeholder="Фамилия">
                    </div>
                </div>
                <div class="col-md-4"></div>
            </div>
            <div class="row">
                <div class="col-md-4"></div>
                <div class="col-md-4">
                    <div class="form-group">
                        <input type="text" name="patronymic" value="${requestScope.patronymic}" class="form-control" placeholder="Отчество">
                    </div>
                </div>
                <div class="col-md-4"></div>
            </div>
            <h4 style="text-align: center">Дата рождения</h4>
            <div class="row">
                <div class="col-md-4"></div>
                <div class="col-md-4">
                    <div class="form-group">
                        <input type="date" birthDate name="birthDate" class="form-control">
                    </div>
                </div>
                <div class="col-md-4"></div>
            </div>
            <div class="row">
                <div class="col-md-4"></div>
                <div class="col-md-4">
                    <div class="form-group">
                        <input type="email" name="email" class="form-control" placeholder="Email">
                    </div>
                </div>
                <div class="col-md-4"></div>
            </div>
            <div class="row">
                <div class="col-md-4"></div>
                <div class="col-md-4">
                    <div class="form-group">
                        <input type="text" name="phone" class="form-control" placeholder="Телефон">
                    </div>
                </div>
                <div class="col-md-4"></div>
            </div>
            <div class="row">
                <div class="col-md-4"></div>
                <div class="col-md-4">
                    <div class="form-group">
                        <input type="text" name="inn" class="form-control" placeholder="ИНН">
                    </div>
                </div>
                <div class="col-md-4"></div>
            </div>
            <div class="row">
                <div class="col-md-4"></div>
                <div class="col-md-4">
                    <div class="form-group">
                        <input type="text" name="passport" class="form-control" placeholder="Серия и номер паспорта">
                    </div>
                </div>
                <div class="col-md-4"></div>
            </div>
            <div class="row">
                <div class="col-md-4"></div>
                <div class="col-md-4">
                    <div class="form-group">
                        <input type="text" name="issuedBy" class="form-control" placeholder="Кем выдан паспорт">
                    </div>
                </div>
                <div class="col-md-4"></div>
            </div>
            <div class="row">
                <div class="col-md-4"></div>
                <div class="col-md-4">
                    <div class="form-group">
                        <input type="text" name="issuedWhen" class="form-control" placeholder="Когда выдан паспорт">
                    </div>
                </div>
                <div class="col-md-4"></div>
            </div>
            <div class="row">
                <div class="col-md-4"></div>
                <div class="col-md-4">
                    <div class="form-group">
                        <h4>Первая страница паспорта</h4>
                        <input type="file" name="f1">
                    </div>
                </div>
                <div class="col-md-4"></div>
            </div>
            <div class="row">
                <div class="col-md-4"></div>
                <div class="col-md-4">
                    <div class="form-group">
                        <h4>Вторая страница паспорта</h4>
                        <input type="file" name="f2">
                    </div>
                </div>
                <div class="col-md-4"></div>
            </div>
            <div class="row">
                <div class="col-md-4"></div>
                <div class="col-md-4">
                    <div class="form-group">
                        <h4>Прописка</h4>
                        <input type="file" name="f3">
                    </div>
                </div>
                <div class="col-md-4"></div>
            </div>
            <div class="row">
                <div class="col-md-4"></div>
                <div class="col-md-4">
                    <div class="form-group">
                        <h4>Ваше фото</h4>
                        <input type="file" name="f4">
                    </div>
                </div>
                <div class="col-md-4"></div>
            </div>
            <div class="row">
                <div class="col-md-4"></div>
                <div class="col-md-4">
                    <div class="form-group">
                        <select class="form-control" name="bank">
                            <option>Приват</option>
                            <option>Ощадбанк</option>
                            <option>Альфабанк</option>
                        </select>
                    </div>
                </div>
                <div class="col-md-4"></div>
            </div>
            <div class="row">
                <div class="col-md-4"></div>
                <div class="col-md-4">
                    <div class="form-group">
                        <input type="text" name="issuedWhen" class="form-control" placeholder="Номер карты">
                    </div>
                </div>
                <div class="col-md-4"></div>
            </div>
            <div class="row">
                <div class="col-md-4"></div>
                <div class="col-md-4">
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary">Отправить</button>
                    </div>
                </div>
                <div class="col-md-4"></div>
            </div>
        </div>
    </form>
</div>


</body>


</html>
