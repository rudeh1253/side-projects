export const shareKakao = (queryString) => {
    if (window.Kakao) {
        console.log("exists");
        const Kakao = window.Kakao;
        if (!Kakao.isInitialized()) {
            Kakao.init(process.env.REACT_APP_KAKAO_API_KEY);
        }
        const completeUrl = `${process.env.REACT_APP_ROOT_URL}?path=${queryString}`;
        console.log(completeUrl);

        Kakao.Link.sendDefault({
            objectType: 'feed',
            content: {
              title: "신년 메시지가 도착했어요",
              imageUrl:
                "https://cdn.pixabay.com/photo/2015/11/26/12/11/happy-new-year-1063797_1280.jpg",
              link: {
                mobileWebUrl: completeUrl,
                webUrl: completeUrl
              },
            },
            buttons: [
              {
                title: '웹으로 이동',
                link: {
                  mobileWebUrl: completeUrl,
                  webUrl: completeUrl
                },
              }
            ],
          });
    } else {
        console.log("no kakao");
    }
};