var url = contextRoot + "/tasks"

var http = new XMLHttpRequest()

http.onreadystatechange = function() {
    if (this.readyState != 4 || this.status != 200) {
        return
    }
    
    response = JSON.parse(this.responseText)
    document.getElementById("tasks").innerHTML = response.task.name
}
    
function listaaTehtavat(){
    http.open("GET", url)
    http.send()
    
    var teksti = document.createElement("p")
    teksti.appendChild(document.createTextNode())
}
