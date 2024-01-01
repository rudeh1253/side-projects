import "./App.css";
import { useEffect, useState } from "react";
import { shareKakao } from "./util/shareKakaoLink";
import { useSearchParams } from "react-router-dom";

function App() {
    const [searchParams, setSearchParams] = useSearchParams();
    const [currentSharer, setCurrentSharer] = useState();
    const [sharers, setSharers] = useState([]);
    const queryString = searchParams.get("path");
    useEffect(() => {
        const script = document.createElement("script");
        script.src = "https://developers.kakao.com/sdk/js/kakao.js";
        script.async = true;
        document.body.appendChild(script);
        if (queryString) {
            setSharers(queryString.split(";"));
        }
        return () => document.body.removeChild(script);
    }, []);
    return (
        <div className="root-container">
            <div>
                <img className="main-img" src="https://cdn.pixabay.com/photo/2015/11/26/12/11/happy-new-year-1063797_1280.jpg" />
            </div>
            <p className="message">행복한 신년 되세요</p>
            <div className="input-group">
                <label htmlFor="name-input" className="name-label">이름</label>
                <input className="name-input" type="text" onChange={e => setCurrentSharer(e.target.value)} id="name-input" />
            </div>
            <button className="btn--share" id="kakaotalk-sharing-btn" onClick={() => {
                if (currentSharer !== "") {
                    sharers.push(currentSharer);
                    shareKakao(sharers.join(";"));
                } else {
                    alert("이름을 입력해 주세요");
                }
            }}>공유하기</button>
            <p className="warning-message">1월 1일 23:59 이후 서버 닫힐 예정</p>
            <div className="relay-container">
                <p className="relay-container-title">릴레이 현황</p>
                <ol className="relay-group">
                    {sharers.map((e, id) => <li className="relay-item" key={id}>{e}</li>)}
                </ol>
            </div>
        </div>
    );
}

export default App;
