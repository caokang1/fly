function setRemFontSize() {
  var remSize = window.innerWidth / 19.2;
  document.querySelector("html").style.fontSize = remSize + "px";
}
setRemFontSize();
window.addEventListener("resize", function () {
  setTimeout(function () {
    setRemFontSize();
  }, 200)
});