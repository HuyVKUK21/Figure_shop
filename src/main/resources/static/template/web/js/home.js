
document.addEventListener('DOMContentLoaded', function() {
    const banner_slide = document.querySelectorAll('.banner-slide');
    const button_left = document.querySelector('.button__left');
    const button_right = document.querySelector('.button__right');
    
    let b = 1;
    let c = 0;
    
    banner_slide.forEach(e => {
        c++;
    });
    
    button_left.addEventListener('click', () => {
        b--;
        slide();
    });
    
    button_right.addEventListener('click', () => {
        b++;
        slide();
    });
    
    function slide() {
        let a = 0;
        banner_slide.forEach(e => {
            a++;
            if (b < 1) {
                b = c;
            } else if (b > c) {
                b = 1;
            }
            
            if (a == b) {
                e.style.display = "block";
                e.style.animation = "fadeIn cubic-bezier(.09,.63,.41,.91) 1s";
            } else {
                e.style.display = "none";
            }
        });
    }
    
    setInterval(() => {
        b++;
        slide();
    }, 5000);
    
    const productContainers = document.querySelectorAll('.cata__contain');
    
    productContainers.forEach((container, containerIndex) => {
        const product = container.querySelector('.product');
        const buttons = container.querySelectorAll('.cata--button');
        let currentPosition = 0; 
        
        if (buttons.length >= 2) {
            const leftButton = buttons[0];  
            const rightButton = buttons[1]; 
            
        
            leftButton.addEventListener('click', () => {
                if (currentPosition > 0) {
                    currentPosition = 0;
                    product.style.transition = "transform 0.5s ease-in-out";
                    product.style.transform = "translateX(0%)";
                }
            });
            
            rightButton.addEventListener('click', () => {
                if (currentPosition === 0) {
                    currentPosition = 1;
                    product.style.transition = "transform 0.5s ease-in-out";
                    product.style.transform = "translateX(-50%)";
                }
            });
        }
    });
});