/**
 * 
 */

var carosello = $(".carosello").get();
  
  function cambioImmagineAvanti()
  {
	  for(var i = 0; i<carosello.length; i++)
	  {
		  console.log(i.toString());

		  if($(carosello[i]).hasClass("active"))
		  {
			  $(carosello[i]).removeClass("active");
			  if(i >= carosello.length-1)
			  {	  
				  $(carosello[0]).addClass("active");
				  break;
			  }
			  else
			  {	 
				  $(carosello[i+1]).addClass("active");
				  break;
			  }
		  }
	  }
  }
  
  
    function cambioImmagineIndietro()
  {
	  for(var i = carosello.length; i>=0; i--)
	  {
		  console.log(i.toString());

		  if($(carosello[i]).hasClass("active"))
		  {
			  $(carosello[i]).removeClass("active");
			  if(i == 0)
			  {	  
				  $(carosello[carosello.length-1]).addClass("active");
				  break;
			  }
			  else
			  {	 
				  $(carosello[i-1]).addClass("active");
				  break;
			  }
		  }
	  }
  }
  

  /* Open when someone clicks on the span element */

/*
$(".apriMenu").mouseenter(function() {
	console.log("ciao");
  // Codice da eseguire quando il mouse entra (hover)
  let i = $(this).find("#myNav")
  if(i== null){console.log("madonna")}
  i.css("width", "90vw");
}).mouseleave(function() {
	console.log("ciao");
  // Codice da eseguire quando il mouse esce (unhover)
  $(this).find("#myNav").css("width", "0vw");
});
*/

function openNav(i) {
	/*console.log("ciao");
	$(this).find("#myNav0").css("width", "90vw");
	console.log("ciao di nuovo");*/
	//$("#divoggetti").css("display", "none")
		let elementiConClasse = document.getElementsByClassName("pulsantiOggetti");

    for (let i = 0; i < elementiConClasse.length; i++) {
      elementiConClasse[i].style.display = "block";
    }
	switch(i){
	
	case 0: document.getElementById("myNav0").style.width = "80vw";
	case 1: document.getElementById("myNav1").style.width = "80vw";
	case 2: document.getElementById("myNav2").style.width = "80vw";
	case 3: document.getElementById("myNav3").style.width = "80vw";
	case 4: document.getElementById("myNav4").style.width = "80vw";
	case 5: document.getElementById("myNav5").style.width = "80vw";
	case 6: document.getElementById("myNav6").style.width = "80vw";
		}
}
function closeNav(i) {
	//$("#divoggetti").css("display", "inline-flex")
	let elementiConClasse = document.getElementsByClassName("pulsantiOggetti");

    for (let i = 0; i < elementiConClasse.length; i++) {
      elementiConClasse[i].style.display = "none";
    }
  	switch(i){
	case 0: document.getElementById("myNav0").style.width = "0vw";
	case 1: document.getElementById("myNav1").style.width = "0vw";
	case 2: document.getElementById("myNav2").style.width = "0vw";
	case 3: document.getElementById("myNav3").style.width = "0vw";
	case 4: document.getElementById("myNav4").style.width = "0vw";
	case 5: document.getElementById("myNav5").style.width = "0vw";
	case 6: document.getElementById("myNav6").style.width = "0vw";
		}
}

