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
        // console.log(response.data);
        console.log([...new Set(response.data.map(item => item.ssotHeader))]);
        setAllHeaders(response.data);
        // findUniqueSSOT()
        // bindToInterface();

        setAllSsotHeaders([...new Set(response.data.map(item => item.ssotHeader))])
        setCounter(true)
        bindOutputHeader();
      })
      .catch((error) => {
        console.log(error);
      });
  }, [counter]);

  function addHeaders(current, ssot) {
    var company = "a"
    var config = {
      headers: { 'Access-Control-Allow-Origin': '*' }
    };
    axios
      .post(
        "http://localhost:8080/addHeader?currentHeader=" + current + "&ssotHeader=" + ssot + "&company=" + company,
        config
      )
      .then((response) => {
        console.log(response.data);
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

  // function bindToInterface() {
  //   for (let i = 0; i < allHeaders.length; i++) {
  //     // console.log(allHeaders[i])
  //     // setInputHeaders(inputHeaders => [...inputHeaders, { id: allHeaders[i].currentHeader, shape: 'interfaceBox' }]);
  //     // if(ouputHeaders.filter((header) => header.id == allHeaders[i].ssotHeader) == []){
  //       setOuputHeaders(ouputHeaders => [...ouputHeaders, { id: allHeaders[i].ssotHeader, shape: 'interfaceBox' }]);
  //     // }
  //     // setLines(lines => [...lines, { props: { start: allHeaders[i].currentHeader, end: allHeaders[i].ssotHeader } }]);
  //   }
  // }

  function bindOutputHeader() {
    for (let i = 0; i < allSsotHeaders.length; i++) {
      setInputHeaders(inputHeaders => [...inputHeaders, { id: i + "a", shape: 'interfaceBox' }]);
      setOuputHeaders(ouputHeaders => [...ouputHeaders, { id: allSsotHeaders[i], shape: 'interfaceBox' }]);
      // setInitialLines(initialLines => [...initialLines, { props: { start: i+"a", end: allSsotHeaders[i] }}])
      setLines(lines => [...lines, { props: { start: i + "a", end: allSsotHeaders[i] } }]);
    }
  }

  function findUniqueSSOT() {
    const unique = [...new Set(ouputHeaders.map(item => item.id))]
    console.log([...new Set(ouputHeaders.map(item => item.id))])
  }

  const [noError, setNoError] = useState(false);

  function checkState() {
    for (let i = 0; i < lines.length; i++) {
      if (lines[i].menuWindowOpened == false) {
        setNoError(true);
        console.log(lines[i].props.start);
        console.log(lines[i].props.end);
        setChangedConnection(changedConnection => [...changedConnection, { start: lines[i].props.start, end: lines[i].props.end }])
        setDisplay(true);
      }
    }
    if (noError == false) {
      console.log("redirect to success page");
    }
  }

  function submit() {
    // addHeaders(lines[i].props.start, lines[i].props.end)
  }

  // selected:{id:string,type:"arrow"|"box"}
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

  function ValidatePetSelection(start) {
    var checkboxes = document.getElementsByName(start);
    for (var i = 0; i < checkboxes.length; i++) {
      if (checkboxes[i].checked) {
        console.log(start + "-" + checkboxes[i].value)
      }
    }

  }
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
      {/* <button onClick={checkstate}> test </button> */}
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
                              <input type="checkbox" id={"EverywhereRemit" + index} name={item.start} value="EverywhereRemit" onClick={() => ValidatePetSelection(item.start)} />
                              <label for={"EverywhereRemit" + index}> EverywhereRemit</label>
                              <input type="checkbox" id={"PaymentGo" + index} name={item.start} value="PaymentGo" onClick={() => ValidatePetSelection(item.start)} />
                              <label for={"PaymentGo" + index}> PaymentGo </label>
                              <input type="checkbox" id={"FinanceNow" + index} name={item.start} value="FinanceNow" onClick={() => ValidatePetSelection(item.start)} />
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