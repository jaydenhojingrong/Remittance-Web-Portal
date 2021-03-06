import React, { useState } from 'react';
import axios from "axios";
// import multer from "multer";

export default function UploadFile() {

  const [selectedFile, setSelectedFile] = useState(undefined);
  const [isFilePicked, setIsFilePicked] = useState(false);
  const [error, setError] = useState(false);
  const [spoil, setSpoil] = useState([]);

  const changeHandler = (event) => {
    setSelectedFile(event.target.files[0]);
    setIsFilePicked(true);
    console.log(event.target.files[0]);
    console.log(isFilePicked);
    console.log(event.target.files[0].name);
  };

  const sendTransaction = () => {
    axios.post(
      "https://prelive.paywho.com/api/smu_send_transaction",
      {
        access_token: localStorage.bearer_token,
        api_name: "paymentgo",
        payload: {
          "remitGivenName": null,
          "remitSurname": null,
          "remitCaType": [
            "1",
            "National ID"
          ],
          "remitCaNo": null,
          "merTransAmount": null,
          "remitCountryCode": null,
          "remitAddress": null,
          "nationality": null,
          "remitPurpose": "131",
          "payeeCaType": [
            "1",
            "National ID"
          ],
          "settleCurrency": "sourceCurrency",
          "transCurrency": "CNY",
          "payeeGivenName": null,
          "payeeSurname": null,
          "payeeBirthDate": null,
          "payeeAccountNo": null,
          "payeePhone": null,
          "payeeBankName": null,
          "payeeBranchName": null,
          "payeeCaNo": null,
          "remitAccountNo": null,
          "sourceOfFunds": [
            "02",
            "Business and investment"
          ]
        },
        api_name: "everywhereremit",
        payload:
        {
          "source_type": "",
          "sender_country": "Singapore",
          "segment": "",
          "sender_legal_name_first": "First Name",
          "sender_legal_name_last": "Last Name",
          "sender_date_of_birth": Date.now(),
          "sender_nationality": ["SGP", "Singapore"],
          "sender_id_type": ["national", "National"],
          "sender_id_country": ["SGP", "Singapore"],
          "sender_id_number": "",
          "sender_currency": "SGD",
          "sender_address_line": "",
          "sender_address_city": "Singapore",
          "sender_address_country": ["SGP", "Singapore"],
          "recipient_type": "bank_account",
          "recipient_country": "CHN",
          "recipient_legal_name_first": "Hi",
          "recipient_legal_name_last": "hello",
          "recipient_mobile_number": "12345678",
          "recipient_account_number": "12345678",
          "recipient_currency": "SGD",
          "units": 1,
          "source_of_funds": [
            "01",
            "Bank Deposit"
          ],
          "remittance_purpose": [
            "001-01",
            "Family/living expense"
          ]
        }
      }
    )
      .then((response) => {
        console.log(response);
        // Store the transaction status IF all fields are valid
        if (response.data.code == 0) {
          alert("You have missing fields, please check your file again!");
        }
        else {
          localStorage.status = response.data.message;
          storeTransaction();
        }
      })
      .catch((error) => {
        console.log(error);
      });
  }

  const storeTransaction = () => {
    var config = {
      headers: { 'Access-Control-Allow-Origin': '*' }
    };
    axios.post(
      "http://localhost:8080/addTransaction/" + localStorage.getItem('username')
      + "/" + selectedFile.name + "/" + "apiName" + "/" + localStorage.getItem('status'), config
      // todo: replace filename.csv & apiName with variable names
    )
      .then((response) => {
        console.log(response);
        // set the variable back to false so that the new uploaded transaction will be reflected in dashboard
        localStorage.loaded = 'false';
        // window.location.replace("/admin/dashboard");
      })
      .catch((error) => {
        console.log(error);
      });
  }

  const submitFile = () => {
    const formData = new FormData();
    formData.append("file", selectedFile);
    const config = {
      headers: {
        'content-type': 'multipart/form-data'
      }
    }
    axios.post(
      "http://localhost:8080/files?fileName="+localStorage.getItem("fileName") + "&fileDownloadUrl=" + localStorage.getItem("fileDownloadURL") + "&username=" + localStorage.getItem("username"), formData, config
    )
      .then((response) => {
        console.log(response.data);
        if (response.data.spoil == "Success") {
          // sendTransaction();
          window.location.replace("/admin/mapping");
        } else {
          setSpoil(response.data.spoil);
          setError(true);
        }

      })
      .catch((error) => {
        console.log(error);
      });
  };

  const extractHeaders = () => {
    const formData = new FormData();
    formData.append("file", selectedFile);
    const config = {
      headers: {
        'content-type': 'multipart/form-data'
      }
    }
    axios.post(
      "http://localhost:8080/files/extractheaders", formData, config
    )
      .then((response) => {
        console.log(response.data);
          localStorage.setItem("fileDownloadURL", response.data.fileDownloadURL);
          localStorage.setItem("fileName", response.data.fileName);
          localStorage.setItem("headers", response.data.headers);
          localStorage.setItem("selectedFile", selectedFile);
          // submitFile();
          window.location.replace("/admin/mapping");

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
            {error &&
              <div className="relative flex flex-col min-w-0 break-words w-full mb-6 shadow-lg rounded-lg bg-red-500 border-0">
                <div class="bg-teal-100 border-t-4 border-teal-500 rounded-b text-teal-900 px-4 py-3 shadow-md" role="alert">
                  <div class="flex">
                    <div class="py-1"><svg class="fill-current h-6 w-6 text-teal-500 mr-4" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20"><path d="M2.93 17.07A10 10 0 1 1 17.07 2.93 10 10 0 0 1 2.93 17.07zm12.73-1.41A8 8 0 1 0 4.34 4.34a8 8 0 0 0 11.32 11.32zM9 11V9h2v6H9v-4zm0-6h2v2H9V5z" /></svg></div>
                    <div>
                      <p class="font-bold text-white">Error encountered, please reupload file</p>
                      {spoil && spoil.map(item => {
                        return (
                          <p class="text-sm text-white">{item} column is invalid (check size/regex).</p>
                        )
                      })}
                    </div>
                  </div>
                </div>
              </div>
            }
            <div className="relative flex flex-col min-w-0 break-words w-full mb-6 shadow-lg rounded-lg bg-blueGray-200 border-0">
              <div className="flex-auto px-4 lg:px-10 py-10 pt-3">
                <h6 className="text-blueGray-500 my-auto text-center font-bold">Upload CSV</h6>
                <div className="justify-center mt-8">
                  <div className="rounded-lg bg-gray-50">
                    <div className="m-4">
                      {/* <label className="inline-block mb-2 text-gray-500">File Upload</label> */}
                      <div className="flex items-center justify-center">
                        <label
                          className="flex flex-col w-full">
                          <div className="flex flex-col items-center justify-center pt-7">
                            <svg xmlns="http://www.w3.org/2000/svg" className="w-8 h-8 text-gray-400 group-hover:text-gray-600"
                              fill="none" viewBox="0 0 24 24" stroke="currentColor">
                              <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2"
                                d="M7 16a4 4 0 01-.88-7.903A5 5 0 1115.9 6L16 6a5 5 0 011 9.9M15 13l-3-3m0 0l-3 3m3-3v12" />
                            </svg>
                            <p className="pt-1 text-sm tracking-wider text-gray-400 group-hover:text-gray-600 mb-4">
                              Attach a file</p>
                          </div>
                          <input type="file" className="opacity-0" name="file" defaultValue={selectedFile} onChange={changeHandler} />
                        </label>
                      </div>
                    </div>
                    <div className="flex justify-center p-2">
                      <button className="w-full px-4 py-2 text-white bg-blueGray-600 rounded shadow-xl" onClick={extractHeaders}>Upload</button>
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