import { BrowserModule } from '@angular/platform-browser';
import { Injectable } from '@angular/core';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppComponent } from './app.component';
import { HttpClientModule, HttpInterceptor, HttpHandler, HttpRequest, HTTP_INTERCEPTORS } from '@angular/common/http'


@Injectable()
export class XhrInterceptor implements HttpInterceptor
{

    intercept(req: HttpRequest<any>, next: HttpHandler)
    {
        const xhr = req.clone({
            headers: req.headers.set('X-Requested-With', 'XMLHttpRequest')
        });
        return next.handle(xhr);
    }
}

@NgModule({
    declarations: [
        AppComponent,

    ],
    imports: [
        BrowserModule,
        HttpClientModule,
        FormsModule,
        ReactiveFormsModule
    ],
    providers: [{ provide: HTTP_INTERCEPTORS, useClass: XhrInterceptor, multi: true }],
    bootstrap: [AppComponent]
})
export class AppModule { }