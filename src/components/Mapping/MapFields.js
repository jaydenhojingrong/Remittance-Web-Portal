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

  useEffect(() => {
    var config = {
      headers: { 'Access-Control-Allow-Origin': '*' }
    };
    axios
      .get(
        "http://localhost:8080/headers", config
      )
      .then((response) => {
        console.log(response);
        // console.log(response.data);
        setAllHeaders(response.data);
        bindToInterface();
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);

  const [interfaces, setInterfaces] = useState([]);

  const [inputHeaders, setInputHeaders] = useState([]);
  const [ouputHeaders, setOuputHeaders] = useState([]);
  const [boxes, setBoxes] = useState([]);
  const [lines, setLines] = useState([]);

  function bindToInterface() {
    for (let i = 0; i < allHeaders.length; i++) {
      console.log(allHeaders[i])
      setInputHeaders(inputHeaders => [...inputHeaders, { id: allHeaders[i].currentHeader, shape: 'interfaceBox' }]);
      setOuputHeaders(ouputHeaders => [...ouputHeaders, { id: allHeaders[i].ssotHeader, shape: 'interfaceBox' }]);
      setLines(lines => [...lines, { props: { start: allHeaders[i].currentHeader, end: allHeaders[i].ssotHeader } }]);
    }
  }

  function checkstate() {
    console.log(lines);
    console.log('test');
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
      <button onClick={checkstate}> test </button>
    </>
  );
}

export default MapFields;