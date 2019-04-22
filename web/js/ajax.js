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

    var url = "listaTeatroByCity";
    url += "?cidade=" + str;
    xmlHttp.onreadystatechange = atualizaInformacoes;
    xmlHttp.open("GET", url, true);
    xmlHttp.send(null);
}
function atualizaInformacoes() {
    if (xmlHttp.readyState === 4 || xmlHttp.readyState === "complete") {
        document.getElementById("infos").innerHTML = xmlHttp.responseText;
    }
}

function teatroSelecionado(str) {
    if (typeof XMLHttpRequest !== "undefined") {
        xmlHttp = new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
    }

    if (xmlHttp === null) {
        alert("Browser does not support XMLHTTP Request");
        return;
    }

    var url = "listaPromocoes";
    url += "?teatro=" + str;
    xmlHttp.onreadystatechange = atualizaInformacoes;
    xmlHttp.open("GET", url, true);
    xmlHttp.send(null);
}