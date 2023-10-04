<script>
    // @ts-nocheck

    /**
     * @type String
     */
    export let title;

    /**
     * @type String
     */
    export let tooltip;

    const initpopup = (c) => {
        //console.log(c);

        new bootstrap.Popover(c, { html: true });
    };
</script>

<button
    use:initpopup={this}
    style="max-width: 32px;"
    type="button"
    class="btn btn-dark btn-sm"
    data-bs-container="body"
    data-bs-toggle="popover"
    data-bs-trigger="focus"
    data-bs-placement="right"
    data-bs-content={tooltip}
    data-bs-original-title={title}>
    <img
        width="16px"
        height="16px"
        src="data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHZpZXdCb3g9IjAgMCA1MTIgNTEyIiBzdHlsZT0iaGVpZ2h0OiAxNnB4OyB3aWR0aDogMTZweDsiPjxwYXRoIGQ9Ik0wIDBoNTEydjUxMkgweiIgZmlsbD0idXJsKCNwYXR0ZXJuKSIgZmlsbC1vcGFjaXR5PSIxIj48L3BhdGg+PGcgY2xhc3M9IiIgdHJhbnNmb3JtPSJ0cmFuc2xhdGUoNCwtMikiIHN0eWxlPSIiPjxwYXRoIGQ9Ik0yNTYgMjFDMTI2LjQyNiAyMSAyMSAxMjYuNDI2IDIxIDI1NnMxMDUuNDI2IDIzNSAyMzUgMjM1IDIzNS0xMDUuNDI2IDIzNS0yMzVTMzg1LjU3NCAyMSAyNTYgMjF6bTAgMzZjMTEwLjExOCAwIDE5OSA4OC44ODIgMTk5IDE5OXMtODguODgyIDE5OS0xOTkgMTk5UzU3IDM2Ni4xMTggNTcgMjU2IDE0NS44ODIgNTcgMjU2IDU3em0tNy4zNTIgMzYuNzQ0Yy04LjIyNyAwLTE1LjMxNyAyLjk3Ni0yMS4yNyA4LjkyOC01Ljc3NiA1Ljk1Mi04LjY2NSAxMi45NTUtOC42NjUgMjEuMDA4IDAgOC4yMjcgMi44OSAxNS4yMyA4LjY2NiAyMS4wMDYgNS45NSA1Ljc3NiAxMy4wNCA4LjY2NiAyMS4yNjggOC42NjYgOC4yMjggMCAxNS4yMy0yLjg5IDIxLjAwNi04LjY2NiA1Ljc3Ny01Ljc3NyA4LjY2Ni0xMi43OCA4LjY2Ni0yMS4wMDYgMC04LjA1My0yLjk3Ni0xNS4wNTYtOC45MjctMjEuMDA4LTUuNzc3LTUuOTUyLTEyLjY5Mi04LjkyOC0yMC43NDUtOC45Mjh6bS02Mi43NTcgODIuNDUzdjI4LjA5Nmg0Ni4yMTV2MTg2LjEzSDE4NS44OXYyNy44MzNoMTQwLjIydi0yNy44MzRoLTQ1LjY5VjE3Ni4xOTdoLTk0LjUzeiIgZmlsbD0iI2ZmZiIgZmlsbC1vcGFjaXR5PSIxIj48L3BhdGg+PC9nPjwvc3ZnPg=="
        alt="Deko 1" />
</button>
