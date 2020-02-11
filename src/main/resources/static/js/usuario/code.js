//mostrar imagem de upload
$(function(){
    $('#file').change(function(){
        const file = $(this)[0].files[0]
        const fileReader = new FileReader()
        fileReader.onloadend = function(){
            $('#img').attr('src', fileReader.result)
        }
        fileReader.readAsDataURL(file);
    });
});
$(function(){
    $('#file').change(function(){
        const file = $(this)[0].files[0]
        const fileReader = new FileReader()
        fileReader.onloadend = function(){
            $('#img1').attr('src', fileReader.result)
        }
        fileReader.readAsDataURL(file);
    });
});
$(function(){
    $('#file').change(function(){
        const file = $(this)[0].files[0]
        const fileReader = new FileReader()
        fileReader.onloadend = function(){
            $('#imgR').attr('src', fileReader.result)
        }
        fileReader.readAsDataURL(file);
    });
});
 
//filtrando para que so seja enviadas imagens
function Checkfiles(){
    var fup = document.getElementById('filename');
    var fileName = fup.value;
    var ext = fileName.substring(fileName.lastIndexOf('.') + 1);

    if(ext =="jpeg"||ext=="jpg"){
        return true;
    }
    else{
        return false;
    }
}