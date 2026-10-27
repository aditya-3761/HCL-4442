import React from 'react'
import { useEffect } from 'react'
function UseFetchApi() {
    const[serverData,setServerData]=React.useState([]);
    useEffect(()=>{
        async function getData(){
            try{
                const serverData = await fetch("http://localhost:8080/");
                const data = await serverData.text();
                console.log(data);
                setServerData(data);
            }
            catch(e){
                console.log(e);
            }
        }
        getData();
    },[])
  return (
    <div>UseFetchApi
    <h2>
        {
        JSON.stringify(serverData)
    }
    </h2>  
  </div>
  )
}

export default UseFetchApi