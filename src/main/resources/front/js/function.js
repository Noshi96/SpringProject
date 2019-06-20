function getStart(){
    console.log('getStart');
    getStartArticle();
    var htmlData="";
    if(sessionStorage.length == 0 ){
        sessionStorage['role']= localStorage.getItem('role')
        console.log("Test");
        console.log(sessionStorage['role']);
        console.log("Test");

    }
    if (sessionStorage.length == 0 ){
        htmlData = '<li class="nav-item">    <a class="nav-link" data-toggle="modal" href="#logowanie">Logowanie</a>  </li> <li class="nav-item"><a class="nav-link" data-toggle="modal" href="#rejestracja">Rejestracja</a>  </li> <li class="nav-item">      <a class="nav-link" href="javascript:getArticlespage(1,3)"  >Artykuły</a>    </li>';
    }
    else{
        if (sessionStorage['role'] == "admin" ){
            htmlData = '<li> <a class="nav-link" href="javascript:getArticlespage(1,3)"  >Artykuły</a>    </li> <li> <a class="nav-link" data-toggle="modal" href="#newArticle">Dodaj Artykuł</a>    </li> <li><a class="nav-link" href="javascript:logout()"  >Wyloguj</a>    </li>';
        }
        else if(sessionStorage['role'] == "user"){
            htmlData = '<li> <a class="nav-link" href="javascript:getArticlespage(1,3)"  >Artykuły</a>    </li>  <li><a class="nav-link" href="javascript:logout()"  >Wyloguj</a>    </li>';
        }else{
            htmlData = '<li class="nav-item">    <a class="nav-link" data-toggle="modal" href="#logowanie">Logowanie</a>  </li> <li class="nav-item"><a class="nav-link" data-toggle="modal" href="#rejestracja">Rejestracja</a>  </li> <li class="nav-item">      <a class="nav-link" href="javascript:getArticlespage(1,3)"  >Artykuły</a>    </li>';
        }
    }
    $('#menu-option').html(htmlData);
}

function getStartArticle(){
    console.log('getStartArticle');
    $.ajax({
        url: "http://localhost:8080/articles/limit=3",
        dataType: "json",
        type: "get",
        success: function (result) {
            var data='';
            var temp="";
            $.each(result, function(index,value) {
                data += `<div class="row ">
                            <div class="col-3"><img class="d-block article-img" src="img/`+value.image+`.jpg" alt="Second slide"></div><div class="col-9">
                                <div class="row"><a class="article-a" data-toggle="modal" href="#article-modal" onclick="showArticle(`+value.id+`)">
                                    <h1>`+value.title+`</h1></a>
                                </div>
                                <div class="row">`+value.articleText.substr(0, 400)+`...</div> `;
                                if(sessionStorage.length > 0){
                                    if(sessionStorage['role'] == "admin"){
                                        data +=`<div class="row"><button type="button" class="btn btn-primary btn-article" data-toggle="modal" href="#editArticle" onclick="editArticle(`+value.id+`)" >Edytuj</button> `;
                                        data +=`<button type="button" class="btn btn-article bg-danger" style="margin-left:20px;color:white" onclick="deleteArticle(`+value.id+`)" >Usuń</button> </div>`;
                                    }
                                }
                            data +=` 
                            </div> 
                        </div>  
                        <hr>`;
                           
            });
                $('#main-article').html(data);
        }
    });
}

function getArticlespage(page,limit){
    console.log('getArticlespage',page,limit);
    $.ajax({
        url: "http://localhost:8080/articleShow/"+page+"/limit="+limit,
        dataType: "json",
        type: "get",
        success: function (result) {
            var data='';
            var temp="";
            $.each(result, function(index,value) {
                data += `<div class="row ">
                            <div class="col-3"><img class="d-block article-img" src="img/`+value.image+`.jpg" alt="Second slide"></div><div class="col-9">
                                <div class="row"><a class="article-a" data-toggle="modal" href="#article-modal" onclick="showArticle(`+value.id+`)">
                                    <h1>`+value.title+`</h1></a>
                                </div>
                                <div class="row">`+value.articleText.substr(0, 400)+`...</div> `;
                                if(sessionStorage.length > 0){
                                    if(sessionStorage['role'] == "admin"){
                                        data +=`<div class="row"><button type="button" class="btn btn-primary btn-article" data-toggle="modal" href="#editArticle" onclick="editArticle(`+value.id+`)" >Edytuj</button> `;
                                        data +=`<button type="button" class="btn btn-article bg-danger" style="margin-left:20px;color:white" onclick="deleteArticle(`+value.id+`)" >Usuń</button> </div>`;
                                    }
                                }
                            data +=` 
                            </div> 
                        </div>  
                        <hr>`;
                           
            });
                $('#main-article').html(data);
                pagelist(limit);
        }
    });
}

function pagelist(limit){
    console.log('pagelist',limit);
    var data='<div class="row justify-content-md-center"><div class="col-auto"><div class="btn-group" role="group" aria-label="strony">';
    $.ajax({
        url: "http://localhost:8080/countedPages/limit="+limit,
        dataType: "json",
        type: "get",
        success: function (result) {
            for (var i=1;i<=result;i++){
                data+= '<button type="button" class="btn btn-secondary" onclick="getArticlespage('+i+','+limit+')">'+i+'</button>'; 
            }   
            data+= '</div></div></div>';
            $('#main-article').append(data);
        }
    });
}
function searchArticles(){
    console.log('searchArticles');
    var search = $('#search-article').val();
    $.ajax({
        url: "http://localhost:8080/searchedTitle/"+search,
        dataType: "json",
        type: "get",
        success: function (result) {
            var data='';
            var temp="";
            $.each(result, function(index,value) {
                data += `<div class="row ">
                            <div class="col-3"><img class="d-block article-img" src="img/`+value.image+`.jpg" alt="Second slide"></div><div class="col-9">
                                <div class="row"><a class="article-a" data-toggle="modal" href="#article-modal" onclick="showArticle(`+value.id+`)">
                                    <h1>`+value.title+`</h1></a>
                                </div>
                                <div class="row">`+value.articleText.substr(0, 400)+`...</div> `;
                                if(sessionStorage.length > 0){
                                    if(sessionStorage['role'] == "admin"){
                                        data +=`<div class="row"><button type="button" class="btn btn-primary btn-article" data-toggle="modal" href="#editArticle" onclick="editArticle(`+value.id+`)" >Edytuj</button> `;
                                        data +=`<button type="button" class="btn btn-article bg-danger" style="margin-left:20px;color:white" onclick="deleteArticle(`+value.id+`)" >Usuń</button> </div>`;
                                    }
                                }
                            data +=` 
                            </div> 
                        </div>  
                        <hr>`;
                           
            });
                $('#main-article').html(data);
        }
    });
}

function login(){
    console.log('login');
    var login = $('#login-input').val();
    var password = $('#password-input').val();
    
    var check = 0;
    $.ajax({
        url: "http://localhost:8080/users/",
        dataType: "json",
        type: "get",
        success: function (result) {           
            $.each(result, function(index,value) {
                if(value.name == login){
                    validate(value,login,password);
                    check = 1;
                }
            });
            if (check != 1){
                alert("Niepoprawne dane");
            }
        }        
    });
}
function logout(){
    console.log('logout');
    var htmlData = '<li class="nav-item">    <a class="nav-link" data-toggle="modal" href="#logowanie">Logowanie</a>  </li> <li class="nav-item">    <a class="nav-link" data-toggle="modal" href="#rejestracja">Rejestracja</a>  </li> <li class="nav-item">      <a class="nav-link" href="javascript:getArticlespage(1,3)"  >Artykuły</a>    </li>';
    $('#menu-option').html(htmlData);
    sessionStorage.clear();
    localStorage.clear();
    getStartArticle();
}

function register(){
    console.log('register');
    var login = $('#login-input-register').val();
    var haslo = $('#password-input-register').val();
    var val = 0;
    $.ajax({
        url: "http://localhost:8080/getByName/"+login,
        dataType: "json",
        async:false,
        type: "get",
        success: function (result) {
            $.each(result, function(index,value) {
                val = 1;                
            });
        }
    });
    

    if(val == 0){
        var data2Send ='{"role": 1 ,"password":"'+haslo+'","name":"'+login+'"}';
        var settings = {
            "async": true,
            "crossDomain": true,
            "url": "http://localhost:8080/users/",
            "method": "POST",
            "headers": {
                "Content-Type": "application/json",
                "Accept": "*/*",
                "Cache-Control": "no-cache",
                "cache-control": "no-cache"
            },
            "processData": false,
            "data": data2Send
            }
        
        $.ajax(settings).done(function (response) {
        console.log(response);
        getStartArticle()
        });
    }
    else{
        alert("Taki użytkownik już istnieje");
    }

}
function validate(response,login,passowrd){
    console.log('validate');
    var correct_login = response.name;
    var correct_password = response.password;
    var role = response.role;
    if (correct_login == login && correct_password == passowrd){
        makeSession(login,role);
        getStart();
    }else{
        alert("Nieprawidłowe dane");
    }
}

function makeSession(login,role){
    console.log("makeSession");
    sessionStorage['userLogin'] = login;
    sessionStorage['role']= role;
    localStorage.setItem('role',role);
}



function showArticle(id){
    console.log('showArticle');
    $.ajax({
        url: "http://localhost:8080/getArticleById/"+id,
        dataType: "json",
        type: "get",
        success: function (result) {
            $.each(result, function(index,value) {
                $('#articleTitle').html(value.title);
                $('#articleBody').html(value.articleText);
            });
        }
    });
    
}

function newArticle(){
    var title = $('#newTitleArticle').val();
    var text = $('#newTextArticle').val();
   
    var data2Send ='{"articleText": "'+text+'","title":"'+title+'","image":"default"}';

    var settings = {
        "async": true,
        "crossDomain": true,
        "url": "http://localhost:8080/articles/",
        "method": "POST",
        "headers": {
            "Content-Type": "application/json",
            "Accept": "*/*",
            "Cache-Control": "no-cache",
            "cache-control": "no-cache"
        },
        "processData": false,
        "data": data2Send
        }
    
    $.ajax(settings).done(function (response) {
    console.log(response);
    getArticlespage(1,3);
    });

}
function editArticle(id){
    $.ajax({
        url: "http://localhost:8080/getArticleById/"+id,
        dataType: "json",
        type: "get",
        success: function (result) {
            $.each(result, function(index,value) {
                $('#article-id').val(value.id);
                $('#editTitleArticle').val(value.title);
                $('#editTextArticle').val(value.articleText);
            });
        }
    });

}
            
function sendEditArticle(){
    var title = $('#editTitleArticle').val();
    var text = $('#editTextArticle').val();
    var id =$('#article-id').val();
    var data2Send ='{"articleText": "'+text+'","title":"'+title+'"}';

    var settings = {
        "async": true,
        "crossDomain": true,
        "url": "http://localhost:8080/articles1/"+id,
        "method": "POST",
        "headers": {
            "Content-Type": "application/json",
            "Accept": "*/*",
            "Cache-Control": "no-cache",
            "cache-control": "no-cache"
        },
        "processData": false,
        "data": data2Send
        }
    
    $.ajax(settings).done(function (response) {
    console.log(response);
    getArticlespage(1,3);
    });
    
}

function deleteArticle(id){
    if(confirm("Czy na pewno chcesz usunąć artykuł?")){  
        console.log("Tak");
        var settings = {
            "async": true,
            "crossDomain": true,
            "url": "http://localhost:8080/delArticle/"+id,
            "method": "POST",
            "headers": {
                "Content-Type": "application/json",
                "Accept": "*/*",
                "Cache-Control": "no-cache",
                "cache-control": "no-cache"
            },
            "processData": false,
            "data": ''
            }
        
        $.ajax(settings).done(function (response) {
            console.log('usunieto');
            getArticlespage(1,3);
        });

    }  
    else{  
        console.log("Nie");  
    }  
}

