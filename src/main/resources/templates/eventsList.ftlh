<#import "parts/common.ftlh" as c>
<#import "parts/pager.ftlh" as p>

<@c.page>
    <div class="mb-0 text-center">
        <h3>События</h3>
    </div>
    <a class="btn btn-primary" href="/user-cars/${id}?event=${(event.id)!''}" role="button">Добавить событие</a>
    <a class="btn btn-primary" href="/user-cars/${id}/events/download" role="button">Экспортировать в xlsx</a>

    <table class="table table-sm mt-3">
        <thead>
        <tr>
            <th scope="col">Дата</th>
            <th scope="col">Событие</th>
            <th scope="col">Материалы</th>
            <th scope="col">Количество</th>
            <th scope="col">Стоимость</th>
            <th scope="col">Пробег</th>
            <th scope="col">Примечание</th>
        </tr>
        </thead>
        <tbody>
        <#list page.content as event>
        <#if event??>
        <tr>
            <td>${event.dateEvent}</td>
            <td>${event.typeOfWork}</td>
            <td>${event.consumables}</td>
            <td>${(event.numberOfLitres)! '-'}</td>
            <td>${(event.price)! '-'}</td>
            <td>${(event.odometerReading)! '-'}</td>
            <td>${(event.note)! ''}</td>
            <td>
                <a class="col btn btn-link"
                   href="/user-cars/${id}?event=${event.id}">
                    <i class="far fa-edit"></i>
                </a>
            </td>
            <td>
                <a class="col btn btn-link" href="/user-cars/${id}/delete?event=${event.id}"><i
                            class="far fa-trash-alt"></i></a>
            </td>
        </tr>
        </tbody>
        </#if>
        </#list>
    </table>

    <@p.pager page url>

    </@p.pager>

</@c.page>

