<#import "parts/common.ftlh" as c>

<@c.page>

    <div class="row">
        <div class="col-3">
            <div class="nav flex-column nav-pills" id="v-pills-tab" role="tablist" aria-orientation="vertical">
                <a class="nav-link active" id="v-pills-profile-tab" data-toggle="pill" href="#profile" role="tab"
                   aria-controls="v-pills-profile" aria-selected="false">Профиль</a>
<#--                <a class="nav-link" id="v-pills-messages-tab" data-toggle="pill" href="#cars" role="tab"-->
<#--                   aria-controls="v-pills-messages" aria-selected="false">Мои автомобили</a>-->
<#--                <a class="nav-link" id="v-pills-settings-tab" data-toggle="pill" href="#settings" role="tab"-->
<#--                   aria-controls="v-pills-settings" aria-selected="false">Настройки</a>-->
            </div>
        </div>
        <div class="col-9">
            <div class="tab-content" id="v-pills-tabContent">
                <div class="tab-pane fade show active" id="profile" role="tabpanel"
                     aria-labelledby="v-pills-profile-tab">
                        ${cars?ifExists}
                    <form method="post">

                        <div class="form-group row">
                            <label class="col-sm-3 col-form-label">Имя пользователя:</label>
                            <div class="col-sm-6">
                                <input readonly type="username" name="username" class="form-control" value="${username}"/>
                            </div>
                        </div>

                        <div class="form-group row">
                            <label class="col-sm-3 col-form-label">Пароль:</label>
                            <div class="col-sm-6">
                                <input type="password" name="password" class="form-control" placeholder="Пароль"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-sm-3 col-form-label">Адрес электронной почты:</label>
                            <div class="col-sm-6">
                                <input type="email" name="email" class="form-control" placeholder="some@domain.com"
                                       value="${email!''}"
                                />
                            </div>
                        </div>
                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                        <button type="submit" class="btn btn-primary">Сохранить</button>
                    </form>
                </div>
<#--                <div class="tab-pane fade" id="cars" role="tabpanel" aria-labelledby="v-pills-messages-tab">-->
<#--                    ...-->
<#--                </div>-->
<#--                <div class="tab-pane fade" id="settings" role="tabpanel" aria-labelledby="v-pills-settings-tab">-->
<#--                    ...-->
<#--                </div>-->
            </div>
        </div>
    </div>





</@c.page>