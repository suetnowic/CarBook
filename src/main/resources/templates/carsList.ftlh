<#import "parts/common.ftlh" as c>
<@c.page>
    <div class="mb-0 text-center">
        <h3>Мой гараж</h3>
    </div>
    <a class="btn btn-primary" href="/user-cars/add" role="button">Добавить автомобиль</a>
    <div class="row" style="justify-content: space-between;">
    <#list cars as car>
        <#if car??>
            <div class="card mt-3" style="width: 18rem; border-radius: 12px">
                <h5 class="card-title text-center mt-3">${car.carBrand} ${car.carModel} ${(car.engineCapacity)!''}</h5>
                <img src="../static/image/car.svg" class="card-img-top" alt="авто" style="padding: 0 20px 0 20px">
                <div class="card-body">
                    <a href="/user-cars/${car.id}/events" class="btn btn-primary"><i class="far fa-calendar"></i> К
                        событиям</a>
                    <a href="/user-cars/edit/${car.id}" class="btn btn-primary" title="Редактировать"><i
                                class="far fa-edit"></i></a>
                    <#--                    <a href="/user-cars/${car.id}/to-archive" class="btn btn-primary" title="Переместить в архив">-->
                    <#--                        <i class="fas fa-archive"></i></a>-->
                </div>
            </div>
        <#else>
            У вас пока нет добавленных автомобилей
        </#if>
    </#list>
    </div>
</@c.page>