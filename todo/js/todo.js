(function() {

    var donelist = document.querySelector("#donelist");
    var newTodolist = document.querySelector("#newTodolist");

    var data = [
        {title:"学习",done:false},
        {title:"吃饭",done:false},
        {title:"看电影",done:false},
        {title:"学习java",done:false}
    ];
    for(var i = 0; i < data.length; i++) {
        var todo = data[i];
        createTodoElement(todo);
    }

    function createTodoElement(todo) {
        var li = document.createElement("li");//动态创建li标签
        var a = document.createElement("a");//动态创建a标签
        var checkspan = document.createElement("span");//动态创建span标签
        var textspan = document.createElement("span");
        var text = document.createTextNode(todo.title);//动态创建文本内容

        checkspan.setAttribute("class","checkbox");//为CheckBox对象添加class属性
        textspan.setAttribute("class","text");//为textspan添加class属性
        a.setAttribute("href","javascript:;");//为a标签添加href属性，只要想使用javascript控制，href属性必须是javascript:;

        textspan.appendChild(text);
        a.appendChild(checkspan);
        li.appendChild(a);
        li.appendChild(textspan);

        if(todo.done) {
            donelist.appendChild(li);
        } else {
            newTodolist.appendChild(li);
        }
    }

    //input回车事件
    var input = document.querySelector("#inputText");

    input.onkeyup = function(event) {
        console.log(event.keyCode);
        if(event.keyCode == 13) {
            var inputValue = input.value;
            if(inputValue.trim()) {
                var todo = {title: inputValue,done:false};
                createTodoElement(todo);
            }
            input.value = "";
        }
    }

    //check点击事件
    document.onclick = function(event) {
        var ev = event.target;
        if(ev.getAttribute("class") == "checkbox") {
            var li = ev.parentNode.parentNode;
            var ul = li.parentNode;
            if(ul.getAttribute("class") == "todolist done") {
                newTodolist.appendChild(li);
            } else {
                donelist.appendChild(li);
            }
        }
    }

})();