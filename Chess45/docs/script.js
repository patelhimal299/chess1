function show(ty)
{
    Count = 0;
    for (var key in methods) {
        var row = document.getElementById(key);
        if ((methods[key] &  ty) != 0) {
            row.style.display = '';
            row.className = (count++ % 2) ? rowColor : altColor;
        }
        else
            row.style.display = 'none';
    }
    updateTab(ty);
}

function updateTab(ty)
{
    for (var val in tabs) {
        var sNode = document.getElementById(tabs[val][0]);
        var spanNode = sNode.firstChild;
        if (val == ty) {
            sNode.className = activeTableTab;
            spanNode.innerHTML = tabs[val][1];
        }
        else {
            sNode.className = tableTab;
            spanNode.innerHTML = "<a href=\"javascript:show("+ val + ");\">" + tabs[val][1] + "</a>";
        }
    }
}