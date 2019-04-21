function cidadeSelecionada(str) {
    if (typeof XMLHttpRequest !== "undefined") {
        xmlHttp = new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
    }

    if (xmlHttp === null) {
        alert("Browser does not support XMLHTTP Request");
        return;
    }

    var url = "TeatroByCity";
    url += "?cidade=" + str;
    //xmlHttp.onreadystatechange = atualizaTeatros;
    xmlHttp.open("GET", url, true);
    xmlHttp.send(null);
}