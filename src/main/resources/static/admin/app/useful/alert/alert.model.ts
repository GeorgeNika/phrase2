// based on https://github.com/cornflourblue/angular2-alert-notifications

export class Alert {
    type: AlertType;
    message: string;
}

export enum AlertType {
    Success,
    Error,
    Info,
    Warning
}