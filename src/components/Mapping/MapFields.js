import React, { useRef, useState, useEffect } from "react";
import { Xwrapper } from "react-xarrows";
import '../../assets/styles/Playground.css';
import Box from '../Playground/Box';
import TopBar from '../Playground/TopBar';
import Xarrow from '../Playground/Xarrow';
import MenuWindow from '../Playground/MenuWindow';
import "../../assets/styles/mapping.css";
import axios from "axios";

function MapFields() {
  const [field, setField] = useState("");
  const [allHeaders, setAllHeaders] = useState([]);
  const [allSsotHeaders, setAllSsotHeaders] = useState([]);
  const [allCsvHeaders, setAllCsvHeaders] = useState([]);
  const [inputHeaders, setInputHeaders] = useState([]);
  const [ouputHeaders, setOuputHeaders] = useState([]);
  const [counter, setCounter] = useState(false);

  useEffect(() => {
    var config = {
      headers: { 'Access-Control-Allow-Origin': '*' }
    };
    axios
      .get(
        "http://localhost:8080/headers", config
      )
      .then((response) => {
        // console.log(response);
        setAllHeaders(response.data);
        setAllCsvHeaders(localStorage.getItem("headers").split(","));
        setAllSsotHeaders([...new Set(response.data.map(item => item.ssotHeader))])
        bindHeaders();
        setCounter(true)

      })
      .catch((error) => {
        console.log(error);
      });
  }, [counter]);

  function callBackend() {
    var config = {
      headers: { 'Access-Control-Allow-Origin': '*' }
    };
    axios
      .post(
        "http://localhost:8080/processFile?fileName=" + localStorage.getItem("fileName") + "&fileDownloadURL=" + localStorage.getItem("fileDownloadURL"),
        config
      )
      .then((response) => {
        console.log(response.data);
      })
      .catch((error) => {
        console.log(error);
      });
  }

  function addHeaders(current, ssot, company, api) {
    var config = {
      headers: { 'Access-Control-Allow-Origin': '*' }
    };
    axios
      .post(
        "http://localhost:8080/addHeader?currentHeader=" + current + "&ssotHeader=" + ssot + "&company=" + company +"&apiHeader=" +api,
        config
      )
      .then((response) => {
        console.log(response.data);
      })
      .catch((error) => {
        console.log(error);
      });
  }

  function getApiHeader(current, ssot, company) {
    var config = {
      headers: { 'Access-Control-Allow-Origin': '*' }
    };
    axios
      .get(
        "http://localhost:8080/headers/getApiHeader/" + ssot + "/" + company,
        config
      )
      .then((response) => {
        console.log(response.data);
        if(response.data.apiHeader!=undefined){
          addHeaders(current, ssot, company, response.data.apiHeader)
        }
      })
      .catch((error) => {
        console.log(error);
      });
  }

  function findSsot(current) {
    var config = {
      headers: { 'Access-Control-Allow-Origin': '*' }
    };
    axios
      .get(
        "http://localhost:8080/headers/" + current,
        config
      )
      .then((response) => {
        // console.log(response.data);
        if (response.data.ssotHeader != undefined) {
          setLines(lines => [...lines, { props: { start: current, end: response.data.ssotHeader } }]);
        }
      })
      .catch((error) => {
        console.log(error);
      });
  }

  const [interfaces, setInterfaces] = useState([]);

  const [boxes, setBoxes] = useState([]);
  const [lines, setLines] = useState([]);
  const [changedConnection, setChangedConnection] = useState([]);
  const [display, setDisplay] = useState(false);

  function bindHeaders() {
    for (let i = 0; i < allSsotHeaders.length; i++) {
      setOuputHeaders(ouputHeaders => [...ouputHeaders, { id: allSsotHeaders[i], shape: 'interfaceBox' }]);
    }
    for (let i = 0; i < allCsvHeaders.length; i++) {
      setInputHeaders(inputHeaders => [...inputHeaders, { id: allCsvHeaders[i], shape: 'interfaceBox' }]);
      findSsot(allCsvHeaders[i]);
    }
  }

  const [noError, setNoError] = useState(false);

  function checkState() {
    for (let i = 0; i < lines.length; i++) {
      if (lines[i].menuWindowOpened == false) {
        setNoError(true);
        setChangedConnection(changedConnection => [...changedConnection, { start: lines[i].props.start, end: lines[i].props.end }])
        setDisplay(true);
      }
    }
    if (noError == false) {
      console.log("redirect to success page");
    }
  }

  function submit() {
    for(let i = 0; i < changedConnection.length; i++){
      let checkboxes = document.getElementsByName(changedConnection[i].start);
      for (let j = 0; j < checkboxes.length; j++) {
        if (checkboxes[j].checked) {
            getApiHeader(changedConnection[i].start, changedConnection[i].end, checkboxes[j].value);
        }
      }
    }
    callBackend();
  }

  const [selected, setSelected] = useState(null);
  const [actionState, setActionState] = useState('Normal');

  const handleSelect = (e) => {
    if (e === null) {
      setSelected(null);
      setActionState('Normal');
    } else setSelected({ id: e.target.id, type: 'box' });
  };

  const checkExsitence = (id) => {
    return [...boxes, ...interfaces].map((b) => b.id).includes(id);
  };
  const props = {
    interfaces,
    setInterfaces,
    boxes,
    setBoxes,
    selected,
    handleSelect,
    actionState,
    setActionState,
    lines,
    setLines,
  };

  const boxProps = {
    boxes,
    setBoxes,
    selected,
    handleSelect,
    actionState,
    setLines,
    lines,
  };

  return (
    <>
      <div className="relative w-full rounded">
        <div className="rounded h-full">
          <div className="relative p-3 flex content-center items-center justify-center min-h-screen-75">
            <div>
              <div className="font-semibold text-lg text-blueGray-700 mx-4 mb-4">
                Mapping of Relevant Fields
              </div>
              <Xwrapper>
                <div className="canvasStyle" id="canvas" onClick={() => handleSelect(null)}>
                  <div
                    className="interfacesBarStyle"
                    onDragOver={(e) => e.preventDefault()}
                    // onDrop={handleDropStatic}
                    id="interfacesInputsBar">
                    <i className="interfaceTitleStyle">Your Headers</i>
                    {inputHeaders
                      .map((itr) => (
                        <Box {...boxProps} key={itr.id} id={itr.id} box={{ ...itr, id: itr.id }} position="static" sidePos="left" />
                      ))}
                  </div>
                  <div
                    id="boxesContainer"
                    className="boxesContainer"
                    onDragOver={(e) => e.preventDefault()}
                  // onDrop={handleDropDynamic}
                  >
                    <TopBar {...props} />

                    {boxes.map((box) => (
                      <Box {...boxProps} key={box.id} box={box} position="absolute" sidePos="middle" />
                    ))}
                  </div>
                  <div
                    className="interfacesBarStyle"
                    onDragOver={(e) => e.preventDefault()}
                    // onDrop={handleDropStatic}
                    id="interfacesOutputsBar">
                    <i className="interfaceTitleStyle">Our Headers</i>
                    {ouputHeaders
                      .map((itr) => (
                        <Box {...boxProps} key={itr.id} id={itr.id} box={{ ...itr, id: itr.id }} position="static" sidePos="right" />
                      ))}
                  </div>
                  {/* xarrow connections*/}
                  {lines.map((line, i) => (
                    <Xarrow
                      key={line.props.root + '-' + line.props.end + i}
                      line={line}
                      selected={selected}
                      setSelected={setSelected}
                    />
                  ))}

                  {/* boxes menu that may be opened */}
                  {lines.map((line, i) =>
                    line.menuWindowOpened ? (
                      <MenuWindow key={line.props.root + '-' + line.props.end + i} setLines={setLines} line={line} />
                    ) : null
                  )}
                </div>
              </Xwrapper>
            </div>
          </div>
        </div>
      </div>
      <div className="flex justify-center p-4">
        <button className="w-full px-4 py-2 text-white bg-teal-500 rounded shadow-xl" onClick={checkState}>Save Changes</button>
      </div>
      {display &&
        <>
          <div className={"m-4"}>
            <div
              className={
                "relative flex flex-col min-w-0 break-words w-full m-6 shadow-lg rounded " +
                "bg-lightBlue-900 text-white"
              }
            >
              <div className="rounded-t mb-0 px-4 py-3 border-0">
                <div className="flex flex-wrap items-center">
                  <div className="relative w-full px-4 max-w-full flex-grow flex-1">
                    <h3
                      className={
                        "font-semibold text-lg " +
                        "text-white"
                      }
                    >
                      Changes of Mapped Fields
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

                          "bg-lightBlue-800 text-lightBlue-300 border-lightBlue-700"
                        }
                      >
                        Your Headers
                      </th>
                      <th
                        className={
                          "px-6 align-middle border border-solid py-3 text-xs uppercase border-l-0 border-r-0 whitespace-nowrap font-semibold text-left " +
                          "bg-lightBlue-800 text-lightBlue-300 border-lightBlue-700"
                        }
                      >
                        Our Headers
                      </th>
                      <th
                        className={
                          "px-6 align-middle border border-solid py-3 text-xs uppercase border-l-0 border-r-0 whitespace-nowrap font-semibold text-left " +
                          "bg-lightBlue-800 text-lightBlue-300 border-lightBlue-700"
                        }
                      >
                        Company
                      </th>
                      <th
                        className={
                          "px-6 align-middle border border-solid py-3 text-xs uppercase border-l-0 border-r-0 whitespace-nowrap font-semibold text-left " +
                          "bg-lightBlue-800 text-lightBlue-300 border-lightBlue-700"
                        }
                      ></th>
                    </tr>
                  </thead>
                  <tbody>
                    {changedConnection.map((item, index) => {
                      return (
                        <tr>

                          <td className="border-t-0 px-6 align-middle border-l-0 border-r-0 text-xs whitespace-nowrap p-4">
                            {item.start}
                          </td>
                          <td className="border-t-0 px-6 align-middle border-l-0 border-r-0 text-xs whitespace-nowrap p-4">
                            {item.end}
                          </td>

                          <td className="border-t-0 px-6 align-middle border-l-0 border-r-0 text-xs whitespace-nowrap p-4">
                            <div className="flex items-center">
                              <input type="checkbox" id={"EverywhereRemit" + index} name={item.start} value="EverywhereRemit" />
                              <label for={"EverywhereRemit" + index}> EverywhereRemit</label>
                              <input type="checkbox" id={"PaymentGo" + index} name={item.start} value="PaymentGo" />
                              <label for={"PaymentGo" + index}> PaymentGo </label>
                              <input type="checkbox" id={"FinanceNow" + index} name={item.start} value="FinanceNow" />
                              <label for={"FinanceNow" + index}> FinanceNow</label>
                            </div>
                          </td>
                          <td className="border-t-0 px-6 align-middle border-l-0 border-r-0 text-xs whitespace-nowrap p-4 text-right">
                          </td>
                        </tr>
                      )
                    })}
                  </tbody>
                </table>
              </div>
            </div>
          </div>
          <div className="flex justify-center p-4">
            <button className="w-full px-4 py-2 text-white bg-blueGray-600 rounded shadow-xl" onClick={submit}>Submit</button>
          </div>
        </>
      }
    </>
  );
}

export default MapFields;