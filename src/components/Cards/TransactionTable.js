import React, { useEffect, useState } from "react";
import PropTypes from "prop-types";
import axios from "axios";

// components

import TableDropdown from "components/Dropdowns/TableDropdown.js";
// import { useEffect, useState } from "react/cjs/react.production.min";


export default function CardTable({ color }) {
  const [data, setData] = useState([])

  // Ensures that transactions are only loaded once
  function loadTransactions(){
    // if the transaction history had yet to be retrieved from database, load it
    if(localStorage.getItem('loaded') == 'false'){
      transactions();
    }
  }
  function transactions() {
    var config = {
      headers: { 'Access-Control-Allow-Origin': '*' }
    };
    axios
      .get(
        "http://localhost:8080/transactions/" + localStorage.getItem('username'), config
      )
      .then((response) => {
        console.log(response.data);
        setData(response.data)
        // set the variable back to true to prevent it from loading transaction history again
        localStorage.loaded = 'true'; 
      })
      .catch((error) => {
        console.log(error);
      });
  };

  return (
    <>
      <div 
      onLoad={loadTransactions()}
        className={
          "relative flex flex-col min-w-0 break-words w-full mb-6 shadow-lg rounded " +
          (color === "light" ? "bg-white" : "bg-lightBlue-900 text-white")
        }
      >
        <div className="rounded-t mb-0 px-4 py-3 border-0">
          <div className="flex flex-wrap items-center">
            <div className="relative w-full px-4 max-w-full flex-grow flex-1">
              <h3
                className={
                  "font-semibold text-lg " +
                  (color === "light" ? "text-blueGray-700" : "text-white")
                }
              >
                Transactions List
              </h3>
            </div>
          </div>
        </div>
        <div className="block w-full overflow-x-auto">
          {/* Projects table */}
          <table className="items-center w-full bg-transparent border-collapse">
            <thead>
              <tr>
                <th
                  className={
                    "px-6 align-middle border border-solid py-3 text-xs uppercase border-l-0 border-r-0 whitespace-nowrap font-semibold text-left " +
                    (color === "light"
                      ? "bg-blueGray-50 text-blueGray-500 border-blueGray-100"
                      : "bg-lightBlue-800 text-lightBlue-300 border-lightBlue-700")
                  }
                >
                  Transaction ID
                </th>

                <th
                  className={
                    "px-6 align-middle border border-solid py-3 text-xs uppercase border-l-0 border-r-0 whitespace-nowrap font-semibold text-left " +
                    (color === "light"
                      ? "bg-blueGray-50 text-blueGray-500 border-blueGray-100"
                      : "bg-lightBlue-800 text-lightBlue-300 border-lightBlue-700")
                  }
                >
                  Filename
                </th>

                <th
                  className={
                    "px-6 align-middle border border-solid py-3 text-xs uppercase border-l-0 border-r-0 whitespace-nowrap font-semibold text-left " +
                    (color === "light"
                      ? "bg-blueGray-50 text-blueGray-500 border-blueGray-100"
                      : "bg-lightBlue-800 text-lightBlue-300 border-lightBlue-700")
                  }
                >
                  Company
                </th>

                <th
                  className={
                    "px-6 align-middle border border-solid py-3 text-xs uppercase border-l-0 border-r-0 whitespace-nowrap font-semibold text-left " +
                    (color === "light"
                      ? "bg-blueGray-50 text-blueGray-500 border-blueGray-100"
                      : "bg-lightBlue-800 text-lightBlue-300 border-lightBlue-700")
                  }
                >
                  Transaction Status
                </th>
              </tr>
            </thead>
            <tbody>
              {data && data.map(item => {
                return (
                  <>
                    <tr>
                      <th className="border-t-0 px-6 align-middle border-l-0 border-r-0 text-xs whitespace-nowrap p-4 text-left flex items-center">
                        {item.transactionId}
                      </th>

                      <td className="border-t-0 px-6 align-middle border-l-0 border-r-0 text-xs whitespace-nowrap p-4">
                        {item.filename}
                      </td>

                      <td className="border-t-0 px-6 align-middle border-l-0 border-r-0 text-xs whitespace-nowrap p-4">
                        {item.company}
                      </td>
                      
                      <td className="border-t-0 px-6 align-middle border-l-0 border-r-0 text-xs whitespace-nowrap p-4">
                      {item.transactionStatus == "Successful" &&
                      <>
                        <i className="fas fa-circle text-emerald-500 mr-2"></i> {item.transactionStatus}
                        </>
                      }
                      {item.transactionStatus == "Pending" &&
                      <>
                        <i className="fas fa-circle text-orange-500 mr-2"></i> {item.transactionStatus}
                        </>
                      }
                      {item.transactionStatus == "Rejected" &&
                      <>
                        <i className="fas fa-circle text-red-500 mr-2"></i> {item.transactionStatus}
                        </>
                      }
                      </td>
                      
                    </tr>
                  </>
                )
              })}
            </tbody>
          </table>
        </div>
      </div>
    </>
  );
}

CardTable.defaultProps = {
  color: "light",
};

CardTable.propTypes = {
  color: PropTypes.oneOf(["light", "dark"]),
};
