/** Closure function
SETS lastClick IF setLastClick(x) call
RETURNS lastClick IF setLastClick() call
*/
var setLastClick = (function (x){
	lastClick = -1;
	return function (x)
	{
		if (x === undefined){
			return lastClick; 
		}
		else {
			return lastClick = x;
		}
	};
})();

/**
For mobile support to assist with collapsing drop-down menus
*/
function closeDropDown(x){
	if ("ontouchstart" in document.documentElement)	//if statement checks for touch device
	{
		
		if (setLastClick() != x && setLastClick() != -1){
			document.getElementsByClassName("dropdown-content")[setLastClick()].style.display='none';
			document.getElementsByClassName("dropdown-content")[x].style.display='block';
		}
		else if (setLastClick() == -1){
			document.getElementsByClassName("dropdown-content")[x].style.display='block';
		}
		else if (setLastClick() == x){
			
			if (document.getElementsByClassName("dropdown-content")[x].style.display == 'block'){
				document.getElementsByClassName("dropdown-content")[x].style.display='none';
			}
			else if (document.getElementsByClassName("dropdown-content")[x].style.display == 'none'){
				document.getElementsByClassName("dropdown-content")[x].style.display='block';
			}
		}
		setLastClick(x);
	}
}

