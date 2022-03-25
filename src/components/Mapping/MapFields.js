import React, { useRef, useState } from "react";
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
  const getFields = () => {
    axios
      .get(
        "localhost:8080/headers/" + field
      )
      .then((response) => {
        console.log(response);
        console.log(response.data.ssotHeader);
      })
      .catch((error) => {
        console.log(error);
      });
  };
  const [interfaces, setInterfaces] = useState([
    {
      id: 'first name',
      shape: 'interfaceBox',
      type: 'input',
    },
    {
      id: 'last name',
      shape: 'interfaceBox',
      type: 'input',
    },
    {
      id: 'address',
      shape: 'interfaceBox',
      type: 'input',
    },
    {
      id: 'country',
      shape: 'interfaceBox',
      type: 'input',
    },
    {
      id: 'static6',
      shape: 'interfaceBox',
      type: 'input',
    },
    {
      id: 'static123',
      shape: 'interfaceBox',
      type: 'input',
    },
    {
      id: 'a',
      shape: 'interfaceBox',
      type: 'input',
    },
    {
      id: 'c',
      shape: 'interfaceBox',
      type: 'input',
    },
    {
      id: 's',
      shape: 'interfaceBox',
      type: 'input',
    },
    {
      id: 'd',
      shape: 'interfaceBox',
      type: 'input',
    },
    {
      id: 'w',
      shape: 'interfaceBox',
      type: 'input',
    },
    {
      id: 'residence',
      shape: 'interfaceBox',
      type: 'output',
    },
    {
      id: 'f name',
      shape: 'interfaceBox',
      type: 'output',
    },
  ]);

  const [inputHeaders, setInputHeaders] = useState([
    {
      id: 'first name',
      shape: 'interfaceBox',
    },
    {
      id: 'last name',
      shape: 'interfaceBox',
    },
    {
      id: 'address',
      shape: 'interfaceBox',
    },
    {
      id: 'country',
      shape: 'interfaceBox',
    },
    {
      id: 'static6',
      shape: 'interfaceBox',
    },
    {
      id: 'static123456',
      shape: 'interfaceBox',
    },
    {
      id: 'a',
      shape: 'interfaceBox',
    },
    {
      id: 'c',
      shape: 'interfaceBox',
    },
    {
      id: 's',
      shape: 'interfaceBox',
    },
  ]);

  const [ouputHeaders, setOuputHeaders] = useState([
    {
      id: 'residence',
      shape: 'interfaceBox',
    },
    {
      id: 'f name',
      shape: 'interfaceBox',
    },
  ]);

  const [boxes, setBoxes] = useState([]);
  const [lines, setLines] = useState([]);

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
                    {/* {interfaces
                      .filter((itr) => itr.type === 'input')
                      .map((itr) => (
                        <Box {...boxProps} key={itr.id} box={{ ...itr, id: itr.id }} position="static" sidePos="left" />
                      ))} */}
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
                    {/* {interfaces
                      .filter((itr) => itr.type === 'output')
                      .map((itr) => (
                        <Box {...boxProps} key={itr.id} box={{ ...itr, id: itr.id }} position="static" sidePos="right" />
                      ))} */}
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
    </>
  );
}

export default MapFields;