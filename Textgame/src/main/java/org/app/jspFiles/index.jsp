<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>TextGame</title>
    </head>
    <body>
        <p>Пролог</p>
        <p>Вы — искатель приключений, который отправляется в опасное путешествие, чтобы найти древний артефакт. Этот артефакт обладает невероятной силой и может изменить ход истории. Но добраться до него будет непросто. На пути вас ждут испытания, ловушки и загадки.

           В этой игре вам предстоит сделать 10 выборов. Каждый выбор будет иметь последствия, которые повлияют на исход игры. Вы должны выбрать один из трёх или четырёх вариантов ответа. Правильный ответ позволит продолжить повествование, а неправильный приведёт к проигрышу.</p>
        <form name="form1" action="dialog/" method="POST">
            <input name="id" type="hidden" value="0">
            <input type="SUBMIT" value="Начать">
        </form>
        <ul>
            <form action="${pageContext.request.contextPath}/dialog/" method="post">
                <li>
                    Количество игр
                    <span>${gameCount}</span>
                </li>
            </form>
        </ul>
    </body>
</html>