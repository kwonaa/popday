/* swiper */
document.addEventListener('DOMContentLoaded', function() {
    const swiper = new Swiper('.swiper', {
        loop: true,
        slidesPerView: 1,
        pagination: {
            el: '.swiper-pagination',
        },
        autoplay: {
            delay: 3000,
            disableOnInteraction: false,
        },
        navigation: {
            nextEl: '.swiper-button-next',
            prevEl: '.swiper-button-prev',
        },
        scrollbar: {
            el: '.swiper-scrollbar',
        },
    });
});

document.addEventListener('DOMContentLoaded', function() {
    if (window.mainSwiper) {
        window.mainSwiper.init();
    }
});

/* search */
document.addEventListener("DOMContentLoaded", function() {
    fetch('header.html')
        .then(response => response.text())
        .then(data => {
            document.querySelector('head').innerHTML += data;
        })
        .then(() => {
            const searchBtn = document.querySelector('.btn_search');
            const searchInputBox = document.querySelector('.search_input_box');

            if (!searchBtn) {
                console.error("btn_search 요소를 찾을 수 없습니다.");
                return;
            }
            if (!searchInputBox) {
                console.error("search_input_box 요소를 찾을 수 없습니다.");
                return;
            }

            searchBtn.addEventListener('click', function(e) {
                e.preventDefault();
                searchInputBox.classList.toggle('active');
            });

            document.addEventListener('click', function(e) {
                if (!searchInputBox.contains(e.target) && !searchBtn.contains(e.target)) {
                    searchInputBox.classList.remove('active');
                }
            });
        })
        .catch(error => console.error('Error loading header:', error));
});

/* textarea byte check */
$('.textarea.input_txt').on('input', function() {
    let content = $(this).val(); 
    let bytes = calcBytes(content);
    const maxLength = 2000;

    $('.byte').text(bytes + '/' + maxLength);

    if (bytes > maxLength) {
        alert("최대 2000바이트까지 입력 가능합니다.");
        let trimmedContent = truncateToBytes(content, maxLength);
        $(this).val(trimmedContent);
        $('.byte').text(maxLength + '/' + maxLength);
    }
});

function calcBytes(str) {
    let count = 0;
    for (let i = 0; i < str.length; i++) {
        let charCode = str.charCodeAt(i);
        if (charCode <= 127) {
            count++;
        } else {
            count += 2;
        }
    }
    return count;
}

function truncateToBytes(str, maxBytes) {
    let count = 0;
    let truncated = '';
    for (let i = 0; i < str.length; i++) {
        let charCode = str.charCodeAt(i);
        if (charCode <= 127) {
            count++;
        } else {
            count += 2;
        }
        if (count > maxBytes) {
            break;
        }
        truncated += str.charAt(i);
    }
    return truncated;
}

/* file upload */
const existingUrls = new Set();

function loadFiles(input) {
    const files = input.files;
    const container = document.getElementById('imageView');

    if (files.length + container.getElementsByTagName("img").length > 2) {
        alert("최대 2개의 파일만 업로드할 수 있습니다.");
        input.value = '';
        return;
    }

    for (const file of files) {
        const fileUrl = URL.createObjectURL(file);

        if (!existingUrls.has(fileUrl)) {
            existingUrls.add(fileUrl);

            const wrapper = document.createElement('div');
            wrapper.className = 'image_wrap';

            const newImage = document.createElement("img");
            newImage.src = fileUrl;
            newImage.alt = file.name;
            newImage.title = file.name;

            const btnDelete = document.createElement('button');
            btnDelete.className = 'btn_delete';
            btnDelete.innerHTML = '&times;';
            btnDelete.onclick = function() {
                existingUrls.delete(fileUrl);
                wrapper.parentNode.removeChild(wrapper); 
                URL.revokeObjectURL(fileUrl);
                input.value = '';
            };

            wrapper.appendChild(newImage);
            wrapper.appendChild(btnDelete);

            container.appendChild(wrapper);
        }
    }
}

function clearFileInput(input) {
    input.value = '';
}

const fileInput = document.querySelector('.file_upload');

fileInput.addEventListener('change', function() {
    const addImageDiv = document.querySelector('.image_area .add_image');
    addImageDiv.style.border = 'none';
});

document.addEventListener('DOMContentLoaded', (event) => {
    const loginMessage = document.getElementById("loginMessage");
    
    if (loginMessage) {
        if (loginMessage.textContent.trim()) {
            loginMessage.classList.remove("hidden");
        } else {
            loginMessage.classList.add("hidden");
        }
    }
});