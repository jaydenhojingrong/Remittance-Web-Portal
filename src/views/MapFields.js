import React, { useState } from 'react';
import '../assets/styles/Playground.css';
import Box from 'components/Playground/Box';
import TopBar from 'components/Playground/TopBar';
import Xarrow from 'components/Playground/Xarrow';
import { Xwrapper } from 'react-xarrows';
import MenuWindow from 'components/Playground/MenuWindow';

import Navbar from "components/Navbars/AuthNavbar.js";
import Footer from "components/Footers/Footer.js";

const shapes = ['wideBox', 'tallBox', 'interfaceBox'];

export default function MapFields() {
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
            id: 'static8',
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

    //   const handleDropDynamic = (e) => {
    //     let shape = e.dataTransfer.getData('shape');
    //     if (shapes.includes(shape)) {
    //       let l = boxes.length;
    //       while (checkExsitence('box' + l)) l++;
    //       let { x, y } = e.target.getBoundingClientRect();
    //       var newName = prompt('Enter box name: ', 'box' + l);
    //       if (newName) {
    //         let newBox = { id: newName, x: e.clientX - x, y: e.clientY - y, shape };
    //         setBoxes([...boxes, newBox]);
    //       }
    //     }
    //   };

    //   const handleDropStatic = (e) => {
    //     let shape = e.dataTransfer.getData('shape');
    //     if (shapes.includes(shape)) {
    //       let l = interfaces.length;
    //       while (checkExsitence('static' + l)) l++;
    //       let newName = prompt('Enter interface name: ', 'static' + l);
    //       let d = { interfacesInputsBar: 'input', interfacesOutputsBar: 'output' };
    //       if (newName) {
    //         let newItr = { id: newName, shape, type: d[e.target.id] };
    //         setInterfaces([...interfaces, newItr]);
    //       }
    //     }
    //   };

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
    return (<>
        <Navbar transparent />
        <main>
            <div className="relative pt-16 pb-32 flex content-center items-center justify-center min-h-screen-75">
                <div>
                    <h1>
                        Mapping of Relevant Fields
                    </h1>
                    <Xwrapper>
                        <div className="canvasStyle" id="canvas" onClick={() => handleSelect(null)}>
                            {/* <div className="toolboxMenu">
                        <div className="toolboxTitle">Drag & drop me!</div>
                        <hr />
                        <div className="toolboxContainer">
                            {shapes.map((shapeName) => (
                                <div
                                    key={shapeName}
                                    className={shapeName}
                                    onDragStart={(e) => e.dataTransfer.setData('shape', shapeName)}
                                    draggable>
                                    {shapeName}
                                   
                                </div>
                            ))}
                        </div>
                    </div> */}
                            <div
                                className="interfacesBarStyle"
                                onDragOver={(e) => e.preventDefault()}
                                // onDrop={handleDropStatic}
                                id="interfacesInputsBar">
                                <u className="interfaceTitleStyle">Your Headers</u>
                                {interfaces
                                    .filter((itr) => itr.type === 'input')
                                    .map((itr) => (
                                        <Box {...boxProps} key={itr.id} box={{ ...itr, id: itr.id }} position="static" sidePos="left" />
                                    ))}
                            </div>
                            <div
                                id="boxesContainer"
                                className="boxesContainer"
                                onDragOver={(e) => e.preventDefault()}
                            // onDrop={handleDropDynamic}
                            >
                                {/* <TopBar {...props} /> */}

                                {boxes.map((box) => (
                                    <Box {...boxProps} key={box.id} box={box} position="absolute" sidePos="middle" />
                                ))}
                            </div>
                            <div
                                className="interfacesBarStyle"
                                onDragOver={(e) => e.preventDefault()}
                                // onDrop={handleDropStatic}
                                id="interfacesOutputsBar">
                                <u className="interfaceTitleStyle">Our Headers</u>
                                {interfaces
                                    .filter((itr) => itr.type === 'output')
                                    .map((itr) => (
                                        <Box {...boxProps} key={itr.id} box={{ ...itr, id: itr.id }} position="static" sidePos="right" />
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
                    <TopBar {...props} />
                </div>
            </div>


        </main>
        <Footer />
    </>)
}