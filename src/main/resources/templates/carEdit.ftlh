<#import "parts/common.ftlh" as c>

<@c.page>
    <div class="mb-0 text-center">
        <h3>Редактирование автомобиля</h3>
    </div>
    <form method="post" enctype="multipart/form-data">
        <div class="form-group row">
            <div class="col-sm-6">
                <input type="hidden" value="${(car.id)!''}" name="id"/>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-3 col-form-label">Марка:</label>
            <div class="col-sm-6">
                <input type="text" value="${(car.carBrand)!''}" name="carBrand" placeholder="Марка"/>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-3 col-form-label">Модель:</label>
            <div class="col-sm-6">
                <input type="text" value="${(car.carModel)!''}" name="carModel" placeholder="Модель"/>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-3 col-form-label">Год выпуска:</label>
            <div class="col-sm-6">
                <input type="text" value="${(car.yearOfIssue)!''}" name="yearOfIssue"
                       placeholder="Год выпуска"/>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-3 col-form-label">Тип топлива:</label>
            <div class="col-sm-6">
                <input type="text" value="${(car.fuelType)!''}" name="fuelType" placeholder="Тип топлива"/>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-3 col-form-label">Цвет:</label>
            <div class="col-sm-6">
                <input type="text" value="${(car.color)!''}" name="color" placeholder="Цвет"/>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-3 col-form-label">Объём двигателя:</label>
            <div class="col-sm-6">
                <input type="text" value="${(car.engineCapacity)! '0'}" name="engineCapacity"
                       placeholder="Объем двигателя, например 2.5"/>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-3 col-form-label">Кол-во лошадиных сил:</label>
            <div class="col-sm-6">
                <input type="text" value="${(car.enginePower)! '0'}" name="enginePower"
                       placeholder="Количество лошадиных сил"/>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-3 col-form-label">Тип коробки:</label>
            <div class="col-sm-6">
                <input type="text" value="${(car.transmission)!''}" name="transmission"
                       placeholder="Тип коробки"/>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-3 col-form-label">Кузов:</label>
            <div class="col-sm-6">
                <input type="text" value="${(car.bodyType)!''}" name="bodyType" placeholder="Кузов"/>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-3 col-form-label">VIN:</label>
            <div class="col-sm-6">
                <input type="text" value="${(car.vin)!''}" name="vin" placeholder="VIN"/>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-3 col-form-label">Регистрационный знак:</label>
            <div class="col-sm-6">
                <input type="text" value="${(car.vrp)!''}" name="vrp" placeholder="Регистрационный знак"/>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-3 col-form-label">Одометр:</label>
            <div class="col-sm-6">
                <input type="text" value="${(car.odometer)!''}" name="odometer" placeholder="Одометр"/>
            </div>
        </div>
        <input type="hidden" value="${_csrf.token}" name="_csrf" />
        <div class="form-group">
            <button type="submit" class="btn btn-primary">Сохранить</button>
        </div>
    </form>
</@c.page>