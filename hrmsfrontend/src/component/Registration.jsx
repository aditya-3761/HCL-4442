import React, { useState } from 'react'

function Registration() {
const[empId,setEmpId]=useState();
const[EmpName,setEmpName]=useState();
const[EmpDesignation,setEmpDesignation]=useState();
const[EmpStack,setEmpStack]=useState();
const[email,setEmail]=useState();
const[password,setPassword]=useState();




async function sendData(e){

    const employee={
        emp_id:Number(empId),
        emp_name:EmpName,
        designation:EmpDesignation,
        stack:EmpStack,
        email:email,
        password:password
    };

try{
  e.preventDefault();                
  const server=await fetch("http://localhost:8080/v1/user/addemp",{
                    method:'Post',
                    body:JSON.stringify(employee),
                    headers:{"Content-Type":"application/json"}
                  });

                  const statue=await server.text();
                  alert(statue);

}catch(e){
 console.log(e)
}
}


  return (
    <div>

<form>

 <div class="form-group">
    <label for="exampleInputEmail1">Empid</label>
    <input type="number" onChange={(e)=>setEmpId(e.target.value)} class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter emp_id" required />
  
  </div>

 <div class="form-group">
    <label for="exampleInputEmail1">Name</label>
    <input type="text" onChange={(e)=>setEmpName(e.target.value)} class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter name" required />
    
  </div>


  <div class="form-group">
    <label for="exampleInputEmail1">Email address</label>
    <input type="email" onChange={(e)=>setEmail(e.target.value)} class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email" />
    <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
  </div>


<div class="form-group">
    <label for="exampleInputEmail1">Designation</label>
    <input type="text" onChange={(e)=>setEmpDesignation(e.target.value)} class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter deisgnation" required />
    
  </div>

<div class="form-group">
    <label for="exampleInputEmail1">Tech stack</label>
    <input type="text" onChange={(e)=>setEmpStack(e.target.value)} class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter Tech stack like test Engineer, Java FSD etc" required />
    
  </div>


  <div class="form-group">
    <label for="exampleInputPassword1">Password</label>
    <input type="password" onChange={(e)=>setPassword(e.target.value)} class="form-control" id="exampleInputPassword1" placeholder="Password" required />
  </div>
  
  <button type="submit" onClick={sendData} class="btn btn-primary">Register</button>
</form>

    </div>
  )
}

export default Registration