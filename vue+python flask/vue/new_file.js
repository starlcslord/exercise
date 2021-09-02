function next() {
	var audios = document.getElementsByTagName("audio");
		for (var i = 0; i < audios.length; i++) {
		    // console.log("audios "+i+"  SRC:" + audios[i].currentSrc);
		    audios[i].addEventListener('ended', function() {
		        // nextSibling 属性返回指定节点之后紧跟的节点，在相同的树层级中。
		        var nextAudio = this.nextSibling.nextSibling;
		        // tagName 属性返回元素的标签名。(大写)
		        if (nextAudio.tagName == "AUDIO") {
		            nextAudio.play();
		        }
		    }, false);
		}
		console.log(audios)
}
