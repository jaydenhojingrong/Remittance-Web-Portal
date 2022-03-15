import React, {useState} from 'react';
import axios from "axios";
// import multer from "multer";

export default function UploadFile() {
  const [selectedFile, setSelectedFile] = useState(undefined);
	const [isFilePicked, setIsFilePicked] = useState(false);

	const changeHandler = (event) => {
		setSelectedFile(event.target.files[0]);
		setIsFilePicked(true);
	};
  
  const submitFile = () => {
    // axios
    //   .post(
    //     "http://localhost:8080/files/",
    //     {
    //       file: selectedFile,
    //     }
    //   )

    axios.post(
      "http://localhost:8080/files/",
      {
              file: selectedFile,
            },
      {
          headers: {
              // "Authorization": "YOUR_API_AUTHORIZATION_KEY_SHOULD_GOES_HERE_IF_HAVE",
              "Content-type": "multipart/form-data",
          },                    
      }
  )
      .then((response) => {
        console.log(response);
        window.location.replace("/admin/dashboard");
      })
      .catch((error) => {
        console.log(error);
      });
  };
    return (
      <>
        <div className="container mx-auto px-4 h-full">
          <div className="flex content-center items-center justify-center h-full">
            <div className="w-full lg:w-6/12 px-4">
              <div className="relative flex flex-col min-w-0 break-words w-full mb-6 shadow-lg rounded-lg bg-blueGray-200 border-0">
                <div className="flex-auto px-4 lg:px-10 py-10 pt-3">
                  <h6 className="text-blueGray-500 my-auto text-center font-bold">Upload CSV</h6>
                  <div className="flex justify-center mt-8">
                    <div className="rounded-lg shadow-xl bg-gray-50">
                        <div className="m-4">
                            <label className="inline-block mb-2 text-gray-500">File Upload</label>
                            <div className="flex items-center justify-center">
                                <label
                                    className="flex flex-col w-full">
                                    <div className="flex flex-col items-center justify-center pt-7">
                                        <svg xmlns="http://www.w3.org/2000/svg" className="w-8 h-8 text-gray-400 group-hover:text-gray-600"
                                            fill="none" viewBox="0 0 24 24" stroke="currentColor">
                                            <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2"
                                                d="M7 16a4 4 0 01-.88-7.903A5 5 0 1115.9 6L16 6a5 5 0 011 9.9M15 13l-3-3m0 0l-3 3m3-3v12" />
                                        </svg>
                                        <p className="pt-1 text-sm tracking-wider text-gray-400 group-hover:text-gray-600">
                                            Attach a file</p>
                                    </div>
                                    <input type="file" className="opacity-0" name="file" defaultValue ={selectedFile} onChange={changeHandler}/>
                                </label>
                            </div>
                        </div>
                        <div className="flex justify-center p-2">
                            <button className="w-full px-4 py-2 text-white bg-sky-500/100 rounded shadow-xl" onClick={submitFile}>Upload</button>
                        </div>
                    </div>
                </div> 
                </div>
              </div>
            </div>
          </div>
        </div>
      </>
    );
  }