import {useState} from 'react'
import { useEffect } from 'react'
function SampleCode({}) {
    const[pointer,setPointer] = useState(100);
    const[counter,setCounter] = useState(10);

    useEffect(()=>{
        console.log("Counter ="+counter);
        console.log("Pointer ="+pointer);
    },[pointer,counter])

    function changeCounter(){
        setCounter(counter+2);
    }
    function changePointer(){
        setPointer(pointer-2);
    }

  return (
    <div>
        {/* <h2>Employee Details</h2>
        <div style={{border: "2px solid red", height: "150px", width: "300px", margin:"30px"}}>
            <h3>Name : {empdata.ename}</h3>
            <h3>EmpId: {empdata.empid}</h3>
        </div> */}
        <h2>Counter Value = {counter}</h2>
        <h2>Pointer Value = {pointer}</h2>
        <button onClick={changeCounter}>Counter</button>
        <button onClick={changePointer}>Pointer</button>
    </div>
  )
}

export default SampleCode