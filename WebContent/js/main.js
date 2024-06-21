document.addEventListener('DOMContentLoaded', function() {
    const mobileMenu = document.querySelector('.mobile-menu');
    const menuList = document.querySelector('.menu-list');
    const video = document.getElementById('mainVideo');
    const playPauseBtn = document.getElementById('playPauseBtn');
    const playIcon = '<i class="fa-solid fa-play"></i><span> 재생</span>';
    const pauseIcon = '<i class="fa-solid fa-pause"></i><span> 멈춤</span>';

    mobileMenu.addEventListener('click', function() {
        menuList.style.display = menuList.style.display === 'flex' ? 'none' : 'flex';
    });

    playPauseBtn.addEventListener('click', () => {
        if (video.paused) {
            video.play();
            playPauseBtn.innerHTML = pauseIcon;
        } else {
            video.pause();
            playPauseBtn.innerHTML = playIcon;
        }
    });

    const sliders = [
        {
            id: 'slider1', videos: [
                { thumbnail: 'https://img.youtube.com/vi/KJLSPwAXL-E/hqdefault.jpg', title: '엔비디아 CEO의 생각', url: 'https://www.youtube.com/watch?v=KJLSPwAXL-E' },
                { thumbnail: 'https://img.youtube.com/vi/Qve-SRfQr1s/hqdefault.jpg', title: '구글부사장이 생각하는비전', url: 'https://www.youtube.com/watch?v=Qve-SRfQr1s&t=938s' },
                { thumbnail: 'https://img.youtube.com/vi/9Tjha6yr9p4/hqdefault.jpg', title: '코딩강사의 소신발언', url: 'https://www.youtube.com/watch?v=9Tjha6yr9p4&t=1081s' },
                { thumbnail: 'https://img.youtube.com/vi/6QT6SMPwFXk/hqdefault.jpg', title: '30년개발자의 조언', url: 'https://www.youtube.com/watch?v=6QT6SMPwFXk&t=1916s' },
                { thumbnail: 'https://img.youtube.com/vi/ax-Q1iedXJA/hqdefault.jpg', title: '구글부사장이 생각하는비전', url: 'https://www.youtube.com/watch?v=ax-Q1iedXJA&t=539s' },
                { thumbnail: 'https://img.youtube.com/vi/Vn2uIk-G_B0/hqdefault.jpg', title: 'GPT-4o와 미래', url: 'https://www.youtube.com/watch?v=Vn2uIk-G_B0' },
                { thumbnail: 'https://img.youtube.com/vi/j8Z5kKUYlKQ/hqdefault.jpg', title: 'CS지식 어디까지 알아야', url: 'https://www.youtube.com/watch?v=j8Z5kKUYlKQ' },
                { thumbnail: 'https://img.youtube.com/vi/JTUIOC2QmR0/hqdefault.jpg', title: '반란군기질이 개발자를 성공시킨다', url: 'https://www.youtube.com/watch?v=JTUIOC2QmR0&t=271s' }
            ]
        },
        {
            id: 'slider2', videos: [
                { thumbnail: 'https://img.youtube.com/vi/krpYcyVjaRs/hqdefault.jpg', title: '돈 벌기 쉬운 언어', url: 'https://www.youtube.com/watch?v=krpYcyVjaRs' },
                { thumbnail: 'https://img.youtube.com/vi/ZZa7AVVK5S0/hqdefault.jpg', title: 'AI 시대 개발자의 마인드', url: 'https://www.youtube.com/watch?v=ZZa7AVVK5S0&t=174s' },
                { thumbnail: 'https://img.youtube.com/vi/KVPjN_8DkhY/hqdefault.jpg', title: 'ZIG가 C를 대체할까?', url: 'https://www.youtube.com/watch?v=KVPjN_8DkhY&t=5s' },
                { thumbnail: 'https://img.youtube.com/vi/wmxVHGvxMvI/hqdefault.jpg', title: '시스템 코딩 종식', url: 'https://www.youtube.com/watch?v=wmxVHGvxMvI' },
                { thumbnail: 'https://img.youtube.com/vi/k-0zJ9nxwrs/hqdefault.jpg', title: 'AI개발 자동화 구현', url: 'https://www.youtube.com/watch?v=k-0zJ9nxwrs' },
                { thumbnail: 'https://img.youtube.com/vi/H0BVEEcA31o/hqdefault.jpg', title: '개발자로 돈버는 방법', url: 'https://www.youtube.com/watch?v=H0BVEEcA31o' },
                { thumbnail: 'https://img.youtube.com/vi/FPYl7nIKRbA/hqdefault.jpg', title: '메모 기술의 왕 노션', url: 'https://www.youtube.com/watch?v=FPYl7nIKRbA' },
                { thumbnail: 'https://img.youtube.com/vi/6dbalv1WEUk/hqdefault.jpg', title: 'GPT5', url: 'https://www.youtube.com/watch?v=6dbalv1WEUk' }
            ]
        },
        {
            id: 'slider3', videos: [
                { thumbnail: 'https://img.youtube.com/vi/GwhKsWoCJN8/hqdefault.jpg', title: '비전공자 5월취업 상황', url: 'https://www.youtube.com/watch?v=GwhKsWoCJN8&t=335s' },
                { thumbnail: 'https://img.youtube.com/vi/_enqMFm7Vng/hqdefault.jpg', title: '서류합격률 40%의 비전공백엔드', url: 'https://www.youtube.com/watch?v=_enqMFm7Vng' },
                { thumbnail: 'https://img.youtube.com/vi/prG5m5AaamY/hqdefault.jpg', title: '개발자 VS AI', url: 'https://www.youtube.com/watch?v=prG5m5AaamY' },
                { thumbnail: 'https://img.youtube.com/vi/acnrtuKMPgk/hqdefault.jpg', title: '개발자도 짤린다.', url: 'https://www.youtube.com/watch?v=acnrtuKMPgk' },
                { thumbnail: 'https://img.youtube.com/vi/0JJnkMvuIJI/hqdefault.jpg', title: '취준생 프론트엔드 개발자', url: 'https://www.youtube.com/watch?v=0JJnkMvuIJI' },
                { thumbnail: 'https://img.youtube.com/vi/W6DNF1o3pG0/hqdefault.jpg', title: '호주개발자', url: 'https://www.youtube.com/watch?v=W6DNF1o3pG0&t=22s' },
                { thumbnail: 'https://img.youtube.com/vi/ersvST8w-cw/hqdefault.jpg', title: '자바 개발자', url: 'https://www.youtube.com/watch?v=ersvST8w-cw&t=4s' },
                { thumbnail: 'https://img.youtube.com/vi/7suwHYBUT6U/hqdefault.jpg', title: '개발자 포기 현상', url: 'https://www.youtube.com/watch?v=7suwHYBUT6U' }
            ]
        }
    ];

    sliders.forEach(slider => {
        const sliderElement = document.getElementById(slider.id);
        slider.videos.forEach(video => {
            const div = document.createElement('div');
            div.className = 'item';
            div.innerHTML = `
                <img src="${video.thumbnail}" alt="${video.title}">
                <p>${video.title}</p>
            `;
            div.addEventListener('click', () => {
                window.open(video.url, '_blank');
            });
            sliderElement.appendChild(div);
        });

        const nextButton = document.querySelector(`.next[data-slider="${slider.id}"]`);
        const prevButton = document.querySelector(`.prev[data-slider="${slider.id}"]`);
        makeSlider(sliderElement, prevButton, nextButton);
    });

    function makeSlider(element, prev, next) {
        next.addEventListener('click', () => {
            const offsetX = element.offsetWidth;
            element.scrollBy(offsetX, 0);
        });
        prev.addEventListener('click', () => {
            const offsetX = element.offsetWidth;
            element.scrollBy(-offsetX, 0);
        });
    }
});

var arr = [];
function cancelEditMode() {
	var editBtn = document.getElementById('edit-btn');
    var saveBtn = document.getElementById('save-btn');
    var infoInputs = document.querySelectorAll('.info input');
    var cancelBtn = document.getElementById('cancelBtn');
    
    infoInputs.forEach(function(input) {
            var span = input.previousElementSibling;
            span.innerText = arr[input.id];
            span.style.display = 'block';
            input.remove();
    });
        editBtn.style.display = 'block';
        saveBtn.style.display = 'none';
        cancelBtn.style.display = 'none';
}

function toggleProfilePanel() {
    var panel = document.getElementById('profile-panel');
    if (panel.style.display === 'none' || panel.style.display === '') {
        panel.style.display = 'block';
    } else {
        panel.style.display = 'none';
    }
}

function toggleEditMode() {
    var editBtn = document.getElementById('edit-btn');
    var saveBtn = document.getElementById('save-btn');
    var infoSpans = document.querySelectorAll('.info span');
    var cancelBtn = document.getElementById('cancelBtn');

	var idx = 1;
    if (editBtn.style.display !== 'none') {
        infoSpans.forEach(function(span) {
            var currentValue = span.innerText;
            span.style.display = 'none';
            var input = document.createElement('input');
            input.type = 'text';
            input.value = currentValue;
            input.style.display = 'block';
            input.style.width = 'calc(100% - 20px)';
            input.style.marginLeft = '10px';
            input.style.marginBottom = '5px';
            input.id = idx;
            arr[input.id] = currentValue;
            idx++;
            span.parentNode.appendChild(input);
        });
        
        editBtn.style.display = 'none';
        saveBtn.style.display = 'block';
        cancelBtn.style.display = 'block';
    }
}
