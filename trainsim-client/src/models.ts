export interface Itinerary
{
    legs: Leg[];
}

export interface Leg
{
    routeId?: string;
    from: Place;
    to: Place;
}

export interface Place
{
    stopId: string;
    arrival: number;
    departure: number;
}