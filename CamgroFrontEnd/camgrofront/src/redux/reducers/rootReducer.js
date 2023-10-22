import { LOGIN, UPDATE_PERSON, UPDATE_TOKEN } from "../actions/actionsCreators";

const initialState = {
    token : null,
    login : false,
    person: null
}

function rootReducer (state = initialState, action){
    switch(action.type){
        case UPDATE_TOKEN:
            return{
                ...state,
                token : action.payload
            }
        case LOGIN:
            console.log("Entre: " + action.payload)
            return{
                ...state,
                login : action.payload
            }
        case UPDATE_PERSON:
            return{
                ...state,
                person: action.payload
            }
        default:
            return state;
    }
}

export default rootReducer

