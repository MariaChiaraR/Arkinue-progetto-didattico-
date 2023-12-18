
	

  // Prima di tutto, attendi che il documento sia pronto
  $(document).ready(function() {
    // Seleziona l'elemento di input tramite l'ID e collega l'evento "onkeyup"
    
    var username = false;
    var password = false;
    var password2 = false;
    var email = false;


    	$('#username').on('keyup', function() {
		if($(this).val())
    
			console.log("username");
			$(this).css('border','2px solid green');

   		 })
    
    	$('#nome').on('keyup', function() {
		if($(this).val())
    
			console.log("nome");
			$(this).css('border','2px solid green');
   		 })
    
        $('#cognome').on('keyup', function() {

			console.log("cognome");
			$(this).css('border','2px solid green');
	
    	})
    
        $('#indirizzo').on('keyup', function() 
        {
			console.log("indirizzo");
			$(this).css('border','2px solid green');

    	})
    
        $('#password').on('keyup', function() {
			
			var valore = $(this).val();
			console.log(valore);
		if(valore.match(/([!@#$%&*()_+=|<>?{}\[\]~-])/) && valore.length>=8 && valore.length<=20 && valore.match(/[a-z]/) && valore.match(/[A-Z]/) && valore.match(/([0-9])/))
		{    
			$(this).css('border','2px solid green');
			password = true;
		}
		else
		{
			$(this).css('border','2px solid red');
			password = false;
		}
    })
    
        $('#password2').on('keyup', function() {
		if($(this).val()== $("#password").val())
		{	console.log("password2");
			$(this).css('border','2px solid green');
			password2 = true;
		}
		else
		{
			$(this).css('border','2px solid red');
			password2 = false;
		}
    })
        $('#email').on('keyup', function() {
			var valore = $(this).val();
			console.log(valore);
		if($(this).val().match(/^[a-z A-Z 0-9_!#$%&'*+/=?{|}~^.-]+@[a-zA-Z0-9.-]+$/))
		{	
			console.log("email");
			$(this).css('border','2px solid green');
			email = true;
		}
		else
		{
			$(this).css('border','2px solid red');
			email = false;
		}
    })
  });
 
  form.on("submit", function(event){
    
    
    if(password == false|| password2 == false||email == false)
    {
		event.preventDefault();
		let stringa = "";
		if(password == false)
		{
			stringa = stringa + "-password non valida, prego Inserire una password di almeno 8 caratteri, con un segno speciale, almeno una lettera maiuscola ed un numero(\r)";
		}
		if(password2 == false)
		{
			stringa = stringa + "-assicurati di aver ripetuto correttamente la password(\r)";
		}
		if(email == false)
		{
			stringa = stringa + "-inserisci un email valida(\r)";
		}
		alert(stringa);

	}
	
	return;
})


