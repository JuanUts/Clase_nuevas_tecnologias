let el, i;
let data = [
  { firstname: "Mauricio", lastname: "Sevilla", age: 57 },
  { firstname: "Jorge", lastname: "Barón", age: 30 },
  { firstname: "Andrés", lastname: "Espinoza", age: 25 },
  { firstname: "Rafael", lastname: "Pérez", age: 52 }
];
let panel = document.querySelector("#panel");


function clearForm() {
  document.querySelector("#fname").value = "";
  document.querySelector("#lname").value = "";
  document.querySelector("#Iage").value = "";
}

function renderItem() {

  panel.textContent = "";
  data.forEach(x => {
    el = document.createElement("option");
    el.innerText = `${x.firstname} ${x.lastname} ${x.age}`;
    panel.append(el);
  });
}

function validar(){

  let fn = document.querySelector("#fname").value;
  let ln = document.querySelector("#lname").value;
  let age = document.querySelector('#Iage').value;


  if(fn.length == 0 || ln.length == 0 || age.length == 0){
    return false;
  }else{
    return true;
  }
}

function create() {

  if(validar()){

    let fn = document.querySelector("#fname").value;
    let ln = document.querySelector("#lname").value;
    let age = document.querySelector('#Iage').value;
    data = [...data, { firstname: fn, lastname: ln, age: age}];
    clearForm();
    console.log(data)
    renderItem();
    alert('Item creado');

  }else{
    alert('Faltan campos por llenar');
  }
  
}

function panelClick() {
  i = panel.selectedIndex;
  document.querySelector("#fname").value = data[i].firstname;
  document.querySelector("#lname").value = data[i].lastname;
  document.querySelector("#Iage").value = data[i].age;
}

function update() {
  data[i].firstname = document.querySelector("#fname").value;
  data[i].lastname = document.querySelector("#lname").value;
  data[i].age = document.querySelector("#Iage").value;
  renderItem();
  alert('Item actualizado');
}

function deleteItem() {
  data.splice(i, 1);
  renderItem();
  alert('Item eliminado');
}

 renderItem();