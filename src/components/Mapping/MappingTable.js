import React, { useRef, useState } from "react";
import Xarrow from "react-xarrows";
import "../../assets/styles/mapping.css";

function MappingTable() {
    const [arrows, setArrows] = useState([]);
    const addArrow = ({ start, end }) => {
        setArrows([...arrows, { start, end }]);
    };
    const connectPointStyle = {
        position: "absolute",
        width: 15,
        height: 15,
        borderRadius: "50%",
        background: "black"
    };
    const connectPointOffset = {
        left: { left: "0px", top: "50%", transform: "translate(-50%, -50%)" },
        right: { left: "100%", top: "50%", transform: "translate(-50%, -50%)" },
        top: { left: "50%", top: "0px", transform: "translate(-50%, -50%)" },
        bottom: { left: "50%", top: "100%", transform: "translate(-50%, -50%)" }
    };

    const ConnectPointsWrapper = ({ boxId, handler, ref0 }) => {
        const ref1 = useRef();

        const [, setPosition] = useState({});
        const [beingDragged, setBeingDragged] = useState(false);
        return (
            <React.Fragment>
                <div
                    className="connectPoint"
                    style={{
                        ...connectPointStyle,
                        ...connectPointOffset[handler]
                    }}
                    draggable
                    onDragStart={e => {
                        setBeingDragged(true);
                        e.dataTransfer.setData("arrow", boxId);
                    }}
                    onDrag={e => {
                        setPosition({}); // <---- just to force re-rendering, to draw arrow with updated value
                        ref1.current.style.position = "fixed";
                        ref1.current.style.left = e.clientX + "px";
                        ref1.current.style.top = e.clientY + "px";
                        ref1.current.style.transform = "none";
                        ref1.current.style.opacity = 0;
                    }}
                    ref={ref1}
                    onDragEnd={e => {
                        ref1.current.style.position = "absolute";
                        ref1.current.style.left = connectPointOffset[handler].left;
                        ref1.current.style.top = connectPointOffset[handler].top;
                        ref1.current.style.transform = connectPointOffset[handler].transform;
                        ref1.current.style.opacity = 0.5;
                        setBeingDragged(false);
                    }}
                />
                {beingDragged ? <Xarrow start={ref0} end={ref1} /> : null}
            </React.Fragment>
        );
    };

    const boxStyle = {
        border: "1px solid black",
        borderRadius: "10px",
        position: "relative",
        padding: "10px",
        margin: "15px 0"
    };

    const Box = ({ text, handler, addArrow, boxId }) => {
        const ref0 = useRef();
        return (
            <div
                id={boxId}
                style={boxStyle}
                ref={ref0}
                onDragOver={e => e.preventDefault()}
                onDrop={e => {
                    if (e.dataTransfer.getData("arrow") === boxId) {
                        console.log(e.dataTransfer.getData("arrow"), boxId);
                    } else {
                        const refs = { start: e.dataTransfer.getData("arrow"), end: boxId };
                        addArrow(refs);
                        console.log("droped!", refs);
                    }
                }}
            >
                {text}
                <ConnectPointsWrapper {...{ boxId, handler, ref0 }} />
            </div>
        );
    };
    const [boxList, setBoxList] = useState(["f name", "l name", "residence country"]);
    const [SSOPHeaderList, setSSOPHeaderList] = useState(["first name", "last name", "country"]);
    return (
        <>
            <div className="relative w-full rounded h-600-px">
                <div className="rounded h-full">
                    <div style={{ display: "flex", justifyContent: "space-evenly", flexWrap: 'wrap' }}>
                        <div>
                            {boxList.map(box => (
                                <Box
                                    text={box}
                                    {...{ addArrow, handler: "right", boxId: box }}
                                />
                            ))}
                        </div>
                        <div>
                            {SSOPHeaderList.map(header => (
                                <Box
                                    text={header}
                                    {...{ addArrow, handler: "left", boxId: header }}
                                />
                            ))}
                            
                        </div>
                        {arrows.map(ar => (
                            <Xarrow
                                start={ar.start}
                                end={ar.end}
                                key={ar.start + "-." + ar.start}
                            />
                        ))}
                    </div></div>
            </div>
        </>
    );
}

export default MappingTable;