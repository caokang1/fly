var initJson;

function initJson(obj){
	initJson = JSON.parse(JSON.stringify(obj));
}

function initForm(form, id){
	form.val(id, initJson);
}

function getInitJson(){
	return initJson;
}

function getInitValue(key){
	if(initJson){
		return initJson[key];
	}
}